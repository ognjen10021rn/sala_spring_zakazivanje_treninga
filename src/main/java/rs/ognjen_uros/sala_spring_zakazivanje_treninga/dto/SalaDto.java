package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class SalaDto {

    private String name;
    private String about;
    private Integer numberOfPersonalTrainers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getNumberOfPersonalTrainers() {
        return numberOfPersonalTrainers;
    }

    public void setNumberOfPersonalTrainers(Integer numberOfPersonalTrainers) {
        this.numberOfPersonalTrainers = numberOfPersonalTrainers;
    }
}
