package rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Sala;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Termin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.UserTermin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.*;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.exception.NotFoundException;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.helper.MessageHelper;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.mapper.SalaMapper;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.SalaRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.TerminRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.TrainingTypeRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.UserTerminRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.secutiry.service.TokenService;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.SalaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SalaServiceImpl implements SalaService {

    private TokenService tokenService;
    private SalaRepository salaRepository;
    private TrainingTypeRepository trainingTypeRepository;
    private RestTemplate userServiceRestTemplate;
    private MessageHelper messageHelper;
    private UserTerminRepository userTerminRepository;
    private String incrementNumberOfSessions;
    private String decrementNumberOfAvailableSpots;
    private String sendZakazanTreningMailForUser;
    private TerminRepository terminRepository;
    private JmsTemplate jmsTemplate;
    private SalaMapper salaMapper;

    public SalaServiceImpl(MessageHelper messageHelper, RestTemplate userServiceRestTemplate,
                           UserTerminRepository userTerminRepository, SalaRepository salaRepository,
                           TokenService tokenService, TerminRepository terminRepository,
                           SalaMapper salaMapper, JmsTemplate jmsTemplate, @Value("${destination.incrementNumberOfSessions}") String incrementNumberOfSessions,
                           @Value("${destination.decreaseNumberOfAvailableSpots}") String decrementNumberOfAvailableSpots,
                           @Value("${destination.sendScheduledTreningForUser}") String sendScheduledTreningForUser,
                           TrainingTypeRepository trainingTypeRepository) {
        this.salaRepository = salaRepository;
        this.tokenService = tokenService;
        this.salaMapper = salaMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.terminRepository = terminRepository;
        this.userTerminRepository = userTerminRepository;
        this.messageHelper = messageHelper;
        this.jmsTemplate = jmsTemplate;
        this.incrementNumberOfSessions = incrementNumberOfSessions;
        this.decrementNumberOfAvailableSpots = decrementNumberOfAvailableSpots;
        this.sendZakazanTreningMailForUser = sendScheduledTreningForUser;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    public Page<TerminDto> findAll(Pageable pageable) {
        return terminRepository.findAll(pageable).map(salaMapper::terminToTerminDto);
    }

    

    @Override
    public void scheduleTermin(UserTerminCreateDto userTerminCreate) {
        ResponseEntity<UserDto> userDtoResponseEntity = null;
        try {

            userDtoResponseEntity = userServiceRestTemplate.exchange("/user/getUser/"
                    + userTerminCreate.getUserId(), HttpMethod.GET, null, UserDto.class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", userTerminCreate.getUserId()));

        } catch (Exception e) {
            e.printStackTrace();
        }

       Termin termin = terminRepository.findById(userTerminCreate.getTerminId())
               .orElseThrow(() -> new NotFoundException(String
                .format("Termin with key: %s not found.", userTerminCreate.getTerminId())));
        UserDto usr = userDtoResponseEntity.getBody();
        // TODO provera ako se blizi termin treninga a nema >= od minimuma onda treba da se salje otkazivanje treninga
        if(termin.getNumberOfAvailableSpots() - 1 < 0){
            System.out.println("NE MOZETE ZAKAZATI");
            return;
        }
        termin.setNumberOfAvailableSpots(termin.getNumberOfAvailableSpots() - 1);
        userTerminRepository.save(new UserTermin(usr.getId(), userTerminCreate.getTerminId(), termin.getTrainingType().getPrice()));
        jmsTemplate.convertAndSend(incrementNumberOfSessions, messageHelper.createTextMessage(new IncrementNumberOfSessionsDto(userTerminCreate.getUserId())));
        Integer price = 0;
        System.out.println(usr.getEmail());
        System.out.println(usr.getNumberOfSessions());
        if(usr.getNumberOfSessions() % 10 != 0){
            price = termin.getTrainingType().getPrice().intValue();
        }
        // username, start, end, bigDecimal
        // Uspesno zakazivanje treninga
        jmsTemplate.convertAndSend(sendZakazanTreningMailForUser, messageHelper.createTextMessage(new SendScheduledTreningConfirmationDto(usr.getFirstName(), usr.getLastName(),
                termin.getStart(), termin.getEnd(), termin.getTrainingType().getName(),
                termin.getSala().getName(), price)));
        // slanje mejla i menadzeru
    }


    @Override
    public void addSala(SalaDto salaDto) {
        Sala sala = new Sala();
        sala.setName(salaDto.getName());
        sala.setAbout(salaDto.getAbout());
        sala.setNumberOfTrainers(salaDto.getNumberOfPersonalTrainers());
        salaRepository.save(sala);
    }

    @Override
    public List<UserTerminResponseDto> getAllZakazaniTreninzi(Long userId) {
        List<UserTermin> userTermin = userTerminRepository.findAllByUserId(userId);
        List<UserTerminResponseDto> userTerminResponseDtoList = new ArrayList<>();
        Long i = 0L;
        for(UserTermin t : userTermin){
            Termin termin = terminRepository.findById(t.getTerminId())
                    .orElseThrow(() -> new NotFoundException(String
                            .format("Termin with key: %s not found.", t.getTerminId())));
            UserTerminResponseDto userTerminResponseDto = new UserTerminResponseDto();
            userTerminResponseDto.setUserId(userId);
            userTerminResponseDto.setId(i);
            i++;
            userTerminResponseDto.setTerminId(termin.getId());
            userTerminResponseDto.setStart(termin.getStart());
            userTerminResponseDto.setEnd(termin.getEnd());
            userTerminResponseDto.setSalaName(termin.getSala().getName());
            userTerminResponseDto.setTrainingName(termin.getTrainingType().getName());
            userTerminResponseDto.setTrainingType(termin.getTrainingType().getTypeOfTraining());
            userTerminResponseDtoList.add(userTerminResponseDto);
            
        }
        return userTerminResponseDtoList;
        
    }

    @Override
    public List<TerminDto> filterTermins(Map<String, String> params) {
        String day = params.get("dayOfTheWeek") != null ? params.get("dayOfTheWeek") : "";
        String trainingtype = params.get("trainingType")!= null ? params.get("trainingType") : "";
//        System.out.println(terminRepository.filterTrainings(trainingtype));
        List<TerminDto> terminDtoList = new ArrayList<>();
        for(Termin termin : terminRepository.filterTrainings(trainingtype, day)){
            TerminDto terminDto = new TerminDto();
            terminDto.setSalaId(termin.getSala().getId());
            terminDto.setMinimumAvailableSpots(termin.getMinimumNumberOfAvailableSpots());
            terminDto.setMaximumAvailableSpots(termin.getMaximumNumberOfAvailableSpots());
            terminDto.setAvailableSpots(termin.getNumberOfAvailableSpots());
            terminDto.setStart(termin.getStart());
            terminDto.setEnd(termin.getEnd());
            terminDto.setTrainingName(termin.getTrainingType().getName());
            terminDto.setTrainingType(termin.getTrainingType().getTypeOfTraining());
            terminDtoList.add(terminDto);
        }

        return terminDtoList;
    }

    @Override
    public void addTrainingtype(TrainingTypeDto trainingTypeDto) {
        TrainingType trainingType = new TrainingType();
        trainingType.setTypeOfTraining(trainingTypeDto.getTypeOfTraining());
        trainingType.setName(trainingTypeDto.getName());
        trainingType.setPrice(trainingTypeDto.getPrice());
        trainingTypeRepository.save(trainingType);
    }

    @Override
    public Void addTermin(TerminDto terminDto) {
        Termin termin = new Termin();
        Sala sala = salaRepository.findById(terminDto.getSalaId())
               .orElseThrow(() -> new NotFoundException(String
                .format("Sala with key: %s not found.", terminDto.getSalaId())));
        TrainingType trainingType = trainingTypeRepository.findByName(terminDto.getTrainingName());
        termin.setSala(sala);
        termin.setStart(terminDto.getStart());
        termin.setEnd(terminDto.getEnd());
        termin.setTrainingType(trainingType);
        termin.setScheduled(false);
        termin.setMinimumNumberOfAvailableSpots(terminDto.getMinimumAvailableSpots());
        termin.setNumberOfAvailableSpots(terminDto.getAvailableSpots());
        termin.setMaximumNumberOfAvailableSpots(terminDto.getMaximumAvailableSpots());
        termin.setDayOfTheWeek(terminDto.getStart().getDayOfWeek().name());
        terminRepository.save(termin);
        return null;
    }

    @Override
    public String unscheduleTermin(UserTerminCreateDto userTerminCreateDto, String jwt) {
        String email = tokenService.extractAllClaims(jwt.substring(7));
        ResponseEntity<UserDto> userDtoResponseEntity = null;
        try {

            userDtoResponseEntity = userServiceRestTemplate.exchange("/user/getUserByEmail/"
                    + email, HttpMethod.GET, null, UserDto.class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", userTerminCreateDto.getUserId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // nije user nego manager
        if(userDtoResponseEntity.getBody() == null){
            ResponseEntity<ManagerDto> managerDtoResponseEntity = null;
            try {

                managerDtoResponseEntity = userServiceRestTemplate.exchange("/user/getUser/"
                        + userTerminCreateDto.getUserId(), HttpMethod.GET, null, ManagerDto.class);

            } catch (HttpClientErrorException e) {
                if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                    throw new NotFoundException(String.format("User with id: %d not found.", userTerminCreateDto.getUserId()));

            } catch (Exception e) {
                e.printStackTrace();
            }
            // manager otkazuje
        }

        UserDto usr = userDtoResponseEntity.getBody();


        return null;
    }


    @Override
    public SalaDto update(SalaDto salaDto, Long id) {
        return null;
    }

    @Override
    public List<SalaDto> availableTermins() {
        return null;
    }
}
