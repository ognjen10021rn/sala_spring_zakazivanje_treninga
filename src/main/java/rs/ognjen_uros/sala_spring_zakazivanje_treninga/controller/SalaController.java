package rs.ognjen_uros.sala_spring_zakazivanje_treninga.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto.SalaDto;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.secutiry.CheckSecurity;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.service.SalaService;

import javax.validation.Valid;

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
    @CheckSecurity(roles = {"ROLE_ADMIN\", \"ROLE_USER"})
    public ResponseEntity<SalaDto> getAllUsers(@RequestHeader("Authorization") String authorization,
                                               Pageable pageable) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
