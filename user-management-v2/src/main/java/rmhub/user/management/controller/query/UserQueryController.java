package rmhub.user.management.controller.query;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rmhub.user.management.model.User;
import rmhub.user.management.service.email.SendEmail;
import rmhub.user.management.service.jwt.JwtService;
import rmhub.user.management.service.query.IUserQueryService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "/*")
public class UserQueryController {
  @Autowired
  private IUserQueryService userQueryService;
  @Autowired
  private JwtService jwtService;
  
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser() {
    return new ResponseEntity<List<User>>(userQueryService.findAll(), HttpStatus.OK);
  }
 
  @RequestMapping(value = "/login/auth", method = RequestMethod.POST)
  public ResponseEntity<?> login(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {
    String result = "";
    HttpStatus httpStatus = null;
    String input = email.trim();
    if (userQueryService.checkLogin(input, password)) {
      result = jwtService.generateTokenLogin(input);
      httpStatus = HttpStatus.OK;
     } else {
       result = "login fail";
       httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
     }
    return new ResponseEntity<String>(result, httpStatus);
  }
  
  @RequestMapping(value = "/reset-pw/{token}", method = RequestMethod.GET)
  public ResponseEntity<User> resetPassword(@PathVariable String token) {
    
    return ResponseEntity.ok(userQueryService.resetPassword(token));
  }
  
  @RequestMapping(value = "/forgot-pw", method = RequestMethod.GET)
  public ResponseEntity<String> forgotPassword(@RequestParam String email) {
    SendEmail sendEmail = new SendEmail();
    String token = jwtService.generateTokenLogin(email);
    
    try {
      sendEmail.sendLink(email, token);
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new ResponseEntity<String>("Check mail: " + email, HttpStatus.OK);
  }
 
}
