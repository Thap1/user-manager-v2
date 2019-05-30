package rmhub.user.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import rmhub.user.management.model.Role;
import rmhub.user.management.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  @Async
  @Query(value = "select * from role r where r.name = ?1 ",  nativeQuery = true)
  Role findByName(String name);
}
