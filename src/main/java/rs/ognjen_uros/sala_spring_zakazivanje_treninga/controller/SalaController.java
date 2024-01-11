package rs.ognjen_uros.sala_spring_zakazivanje_treninga.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Termin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.UserTermin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.SalaDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.TerminDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.TrainingTypeDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.UserTerminCreateDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.secutiry.CheckSecurity;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.SalaService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sala")
public class SalaController {

    private SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<Page<TerminDto>> getAllTermini(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(salaService.findAll(pageable) ,HttpStatus.OK);
    }

    @PostMapping("/addTermin")
    public ResponseEntity<Void> addTermin(@RequestHeader("Authorization") String authorization, @RequestBody TerminDto termin){
        return new ResponseEntity<>(salaService.addTermin(termin), HttpStatus.OK);
    }

    @PostMapping("/scheduleTrening")
    public ResponseEntity<Void> scheduleTrening(@RequestHeader("Authorization") String authorization, @RequestBody UserTerminCreateDto userTerminCreateDto){
        salaService.scheduleTermin(userTerminCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addSala")
    public ResponseEntity<Void> addSala(@RequestHeader("Authorization") String authorization, @RequestBody SalaDto salaDto){
        salaService.addSala(salaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addTrainingType")
    public ResponseEntity<Void> addTrainingtype(@RequestHeader("Authorization") String authorization, @RequestBody TrainingTypeDto trainingTypeDto){
        salaService.addTrainingtype(trainingTypeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<TerminDto>> filterTermin(@RequestHeader("Authorization") String authorization, @RequestParam Map<String,String> params){
        return new ResponseEntity<>(salaService.filterTermins(params) ,HttpStatus.OK);
    }
}
