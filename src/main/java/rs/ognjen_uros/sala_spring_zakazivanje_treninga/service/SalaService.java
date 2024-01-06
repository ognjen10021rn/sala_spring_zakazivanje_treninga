package rs.ognjen_uros.sala_spring_zakazivanje_treninga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.SalaDto;

import java.util.List;

public interface SalaService {

    Page<SalaDto> findAll(Pageable pageable);
    SalaDto add(SalaDto salaDto);
    SalaDto update(SalaDto userDto, Long id);

    List<SalaDto> availableTermins();
}
