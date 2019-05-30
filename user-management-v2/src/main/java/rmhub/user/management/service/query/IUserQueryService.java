package rmhub.user.management.service.query;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import rmhub.user.management.model.User;

public interface IUserQueryService {
  List<User> findAll();
  User findById(int id);
  User findByEmail(String email);
  UserDetails loadUserByUsername(String username);
  boolean checkLogin(String loginName, String password);
  User resetPassword(String token);
}
