package rs.ognjen_uros.sala_spring_zakazivanje_treninga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ognjen_uros.sala_spring_zakazivanje_treninga.domain.TrainingType;

import java.util.Optional;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {

    TrainingType findByName(String name);
    TrainingType findByTypeOfTraining(String typeOfTraining);
}
