package rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean isScheduled;
    private Integer maximumNumberOfAvailableSpots;
    private Integer numberOfAvailableSpots;
    private Integer minimumNumberOfAvailableSpots;
    private String dayOfTheWeek;
    @ManyToOne(optional = false)
    private TrainingType trainingType;
    @ManyToOne(optional = false)
    private Sala sala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfAvailableSpots() {
        return numberOfAvailableSpots;
    }

    public void setNumberOfAvailableSpots(Integer numberOfAvailableSpots) {
        this.numberOfAvailableSpots = numberOfAvailableSpots;
    }

    public Boolean getScheduled() {
        return isScheduled;
    }

    public Integer getMaximumNumberOfAvailableSpots() {
        return maximumNumberOfAvailableSpots;
    }

    public void setMaximumNumberOfAvailableSpots(Integer maximumNumberOfAvailableSpots) {
        this.maximumNumberOfAvailableSpots = maximumNumberOfAvailableSpots;
    }

    public Integer getMinimumNumberOfAvailableSpots() {
        return minimumNumberOfAvailableSpots;
    }

    public void setMinimumNumberOfAvailableSpots(Integer minimumNumberOfAvailableSpots) {
        this.minimumNumberOfAvailableSpots = minimumNumberOfAvailableSpots;
    }

    public void setScheduled(Boolean scheduled) {
        isScheduled = scheduled;
    }

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

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
