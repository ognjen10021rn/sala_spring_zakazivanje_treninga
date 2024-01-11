package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TerminDto {
    private Long salaId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime end;
    private String trainingType;
    private String trainingName;
    private Integer minimumAvailableSpots;
    private Integer availableSpots;
    private Integer maximumAvailableSpots;
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Integer getMinimumAvailableSpots() {
        return minimumAvailableSpots;
    }

    public void setMinimumAvailableSpots(Integer minimumAvailableSpots) {
        this.minimumAvailableSpots = minimumAvailableSpots;
    }

    public Integer getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    public Integer getMaximumAvailableSpots() {
        return maximumAvailableSpots;
    }

    public void setMaximumAvailableSpots(Integer maximumAvailableSpots) {
        this.maximumAvailableSpots = maximumAvailableSpots;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }
    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
}

