package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


import java.time.LocalDateTime;

public class UserTerminResponseDto {

    private Long id;
    private Long terminId;
    private Long userId;
    private String salaName;
    private LocalDateTime start;
    private LocalDateTime end;
    private String trainingName;
    private String trainingType;

    public UserTerminResponseDto(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }

    public String getSalaName() {
        return salaName;
    }

    public void setSalaName(String salaName) {
        this.salaName = salaName;
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

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }
}

