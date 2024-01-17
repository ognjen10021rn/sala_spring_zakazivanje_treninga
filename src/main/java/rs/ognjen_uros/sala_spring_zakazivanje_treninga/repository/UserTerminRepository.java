package rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.Termin;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.UserTermin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserTerminRepository extends JpaRepository<UserTermin, Long> {

    Optional<UserTermin> findAllByUserIdAndTerminId(Long userId, Long terminId);
    List<UserTermin> findAllByUserId(Long userId);
}
