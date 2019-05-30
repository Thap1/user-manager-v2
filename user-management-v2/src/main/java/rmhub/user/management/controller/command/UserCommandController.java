package rmhub.user.management.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rmhub.user.management.model.User;
import rmhub.user.management.service.command.impl.UserCommandServiceImpl;
@RestController
@RequestMapping("/api/v1")
public class UserCommandController {
  @Autowired
  UserCommandServiceImpl userService;
  
  @RequestMapping(value = "/users/create-user", method = RequestMethod.POST)
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
  }
  
  
  @RequestMapping(value = "/users", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@RequestParam String name, @RequestParam String roleName,
      @RequestParam String engineeringBureau) {
    return ResponseEntity.ok(userService.updateUser(name, roleName, engineeringBureau));
  }
  
  @RequestMapping(value = "/users/change-password", method = RequestMethod.POST)
  public ResponseEntity<String> changePassword(@RequestParam int id, String currentPass, 
      @RequestParam String newPass, @RequestParam String confirmPass) {
    return ResponseEntity.ok(userService.changePassword(id, currentPass, newPass, confirmPass));
  }

}
