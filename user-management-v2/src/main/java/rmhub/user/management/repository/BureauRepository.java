package rmhub.user.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import rmhub.user.management.model.EngineeringBureau;
import rmhub.user.management.model.User;

public interface BureauRepository extends JpaRepository<EngineeringBureau, Integer> {
  @Async
  @Query(value = "select * from engineering_bureau e where e.name = ?1  ",  nativeQuery = true)
 EngineeringBureau findByName(String name);
}
