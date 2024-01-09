package rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserTermin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long terminId;
    private BigDecimal price;

    public UserTermin(Long userId, Long terminId, BigDecimal price) {
        this.userId = userId;
        this.terminId = terminId;
        this.price = price;
    }

    public UserTermin() {

    }
}
