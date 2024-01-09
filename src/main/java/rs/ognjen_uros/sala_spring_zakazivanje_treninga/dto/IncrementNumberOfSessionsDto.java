package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


public class IncrementNumberOfSessionsDto {
    private Long userId;
    public IncrementNumberOfSessionsDto(Long userId, Long terminId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public IncrementNumberOfSessionsDto(Long userId) {
        this.userId = userId;
    }
}

