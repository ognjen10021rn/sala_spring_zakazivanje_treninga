package rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String about;

    private Integer numberOfTrainers;


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

    public Integer getNumberOfTrainers() {
        return numberOfTrainers;
    }

    public void setNumberOfTrainers(Integer numberOfTrainers) {
        this.numberOfTrainers = numberOfTrainers;
    }
}
