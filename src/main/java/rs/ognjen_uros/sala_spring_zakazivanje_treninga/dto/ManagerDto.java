package rs.ognjen_uros.sala_spring_zakazivanje_treninga.dto;

public class ManagerDto {

    private String emailManager;
    private String salaName;
    private String firstName;
    private String lastName;
    private String usernameManager;

    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    public String getSalaName() {
        return salaName;
    }

    public void setSalaName(String salaName) {
        this.salaName = salaName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsernameManager() {
        return usernameManager;
    }

    public void setUsernameManager(String usernameManager) {
        this.usernameManager = usernameManager;
    }
}
