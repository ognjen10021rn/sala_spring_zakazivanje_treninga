package rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.SalaDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.mapper.SalaMapper;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.SalaRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository.TerminRepository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.secutiry.service.TokenService;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.SalaService;

import java.util.List;

@Service
@Transactional
public class SalaServiceImpl implements SalaService {

    private TokenService tokenService;
    private SalaRepository salaRepository;
    private TerminRepository terminRepository;
    private SalaMapper salaMapper;

    public SalaServiceImpl(SalaRepository salaRepository, TokenService tokenService, TerminRepository terminRepository, SalaMapper salaMapper) {
        this.salaRepository = salaRepository;
        this.tokenService = tokenService;
        this.salaMapper = salaMapper;
        this.terminRepository = terminRepository;
    }

    @Override
    public Page<SalaDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public SalaDto add(SalaDto salaDto) {
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
