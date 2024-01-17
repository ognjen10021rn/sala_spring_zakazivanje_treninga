package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;


public class UserTerminDto {

    private Long userId;

    public UserTerminDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

