package rs.ognjen_uros.sala_spring_zakazivanje_treninga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Termin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.UserTermin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.SalaDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.TerminDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.TrainingTypeDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.UserTerminCreateDto;

import java.util.List;

public interface SalaService {

    Page<TerminDto> findAll(Pageable pageable);

    void scheduleTermin(UserTerminCreateDto userTerminCreate);
    void addSala(SalaDto salaDto);

    void addTrainingtype(TrainingTypeDto trainingTypeDto);
    Void addTermin(TerminDto termin);
    SalaDto update(SalaDto userDto, Long id);

    List<SalaDto> availableTermins();
}
