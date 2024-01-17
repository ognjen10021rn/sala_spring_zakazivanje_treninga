package rs.ognjen_uros.sala_spring_zakazivanje_treninga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Termin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.UserTermin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.*;

import java.util.List;
import java.util.Map;

public interface SalaService {

    Page<TerminDto> findAll(Pageable pageable);

    void scheduleTermin(UserTerminCreateDto userTerminCreate);
    void addSala(SalaDto salaDto);

    List<UserTerminResponseDto> getAllZakazaniTreninzi(Long userId);
    List<TerminDto> filterTermins(Map<String, String> params);
    void addTrainingtype(TrainingTypeDto trainingTypeDto);
    Void addTermin(TerminDto termin);

    String unscheduleTermin(UserTerminCreateDto userTerminCreateDto, String jwt);
    SalaDto update(SalaDto userDto, Long id);

    List<SalaDto> availableTermins();
}
