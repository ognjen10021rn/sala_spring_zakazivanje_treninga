package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class SalaDto {

    private Long id;
    private String name;
    private String about;
    private Number numberOfPersonalTrainers;
    private List<TrainingType> trainingTypeList = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    public Number getNumberOfPersonalTrainers() {
        return numberOfPersonalTrainers;
    }

    public void setNumberOfPersonalTrainers(Number numberOfPersonalTrainers) {
        this.numberOfPersonalTrainers = numberOfPersonalTrainers;
    }

    public List<TrainingType> getTrainingTypeList() {
        return trainingTypeList;
    }

    public void setTrainingTypeList(List<TrainingType> trainingTypeList) {
        this.trainingTypeList = trainingTypeList;
    }
}
