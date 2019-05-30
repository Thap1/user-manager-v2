package rmhub.user.management.service.command.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rmhub.user.management.exception.BusinessException;
import rmhub.user.management.model.EngineeringBureau;
import rmhub.user.management.model.Role;
import rmhub.user.management.model.RoleName;
import rmhub.user.management.model.User;
import rmhub.user.management.repository.BureauRepository;
import rmhub.user.management.repository.RoleRepository;
import rmhub.user.management.repository.UserRepository;
import rmhub.user.management.service.command.IUserCommandService;
import rmhub.user.management.service.email.SendEmail;
@Service

public class UserCommandServiceImpl implements IUserCommandService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  BureauRepository bureauRepository;
  private Pattern regexPattern;
  private Matcher regMatcher;
  
  SendEmail sendMail = new SendEmail();
  @Override
  public User createUser(User newUser) {
    valiateNewUser(newUser);
    userRepository.save(newUser);
    return newUser;

  }
  @Override
  public boolean valiateNewUser(User newUser) {
    if (newUser.getLoginName().isBlank() || newUser.getEmail().isBlank() || 
      newUser.getRoles().isEmpty()) {
      throw new BusinessException("The (*) fields are mandatory fields", HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsById(newUser.getId())) {
      throw new BusinessException("The id is existed!", HttpStatus.BAD_REQUEST);
    }
    
    validateEmailAddress(newUser.getEmail());
    
    User checkExistByEmail = null;
        checkExistByEmail = userRepository.findByEmail(newUser.getEmail());
    if (checkExistByEmail != null) {
      throw new BusinessException("The email is existed!", HttpStatus.BAD_REQUEST);
    }
//    for (Role role : newUser.getRoles()) {
//      if (role.getName().equals(RoleName.ROLE_USER)) {
//        throw new BusinessException("Unauthoried.Only admin can create new user", HttpStatus.BAD_REQUEST);
//      }
//    }
//    validateEmailAddress(newUser.getEmail());
    return true;
  }
  
  public boolean validateEmailAddress(String emailAddress) {
    
    regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    regMatcher   = regexPattern.matcher(emailAddress);
    if(!regMatcher.matches()) {
        throw new BusinessException("This email address does not exist", HttpStatus.BAD_REQUEST);
    } 
    return true;       
}
  @Override
  public User updateUser(String name, String roleName, String engineeringBureau) {
      valiadteUpdateUser(name, roleName, engineeringBureau);
      User user = userRepository.findByLoginName(roleName);
      user.setLoginName(name);
      user.getRoles().clear();
      Role role = roleRepository.findByName(roleName);
      user.getRoles().add(role);
      user.getEngineeringBureaus().clear();
      EngineeringBureau bureau = bureauRepository.findByName(engineeringBureau);
      user.getEngineeringBureaus().add(bureau);
      userRepository.save(user);
      return user;
  }
  @Override
  public boolean valiadteUpdateUser(String name, String roleName, String engineeringBureau) {
    if (name.isBlank() || roleName.isBlank() || engineeringBureau.isBlank()) {
      throw new BusinessException("The (*) fields are mandatory fields", HttpStatus.BAD_REQUEST);
    }
    User checkExist = null;
    checkExist = userRepository.findByLoginName(name);
    if (checkExist == null) {
      throw new BusinessException("User by name: " + name + " not existed", HttpStatus.BAD_REQUEST);
    }
    Role checkRole = null;
    checkRole = roleRepository.findByName(roleName);
    if (checkRole == null) {
      throw new BusinessException("Role is wrong", HttpStatus.BAD_REQUEST);
    }
    EngineeringBureau checkER = null;
    checkER = bureauRepository.findByName(engineeringBureau);
    if (checkER == null) {
      throw new BusinessException("Not existed Bureau by name: " + engineeringBureau, HttpStatus.BAD_REQUEST);
    }
    return true;
  }
  @Override
  public User deleteByName(String name) {
    User user = userRepository.findByLoginName(name);
    userRepository.deleteById(user.getId());
    return user;
  }
  @Override
  public boolean validateDeleteByName(String name) {
    if (name.isBlank()) {
      throw new BusinessException("The (*) fields are mandatory fields", HttpStatus.BAD_REQUEST);
    }
    return true;
  }
  @Override
  public String changePassword(int id, String currentPass, String newPass, String confirmPass) {
    // TODO Auto-generated method stub
    User input = userRepository.findById(id).get();
    boolean checkCurrentPassword = false;
    if(!currentPass.equals(input.getPassword())) {
      throw new BusinessException("Wrong password", HttpStatus.BAD_REQUEST);
    }
    if (!newPass.equals(confirmPass)) {
        throw new BusinessException("New password or confirm password not same", HttpStatus.BAD_REQUEST);
    }
    
    input.setPassword(newPass);
    userRepository.save(input);
    try {
      sendMail.sendPassword(input.getEmail(), newPass);
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "Changing password is success";
  }
  @Override
  public String resetPassword(String email) {
    
    return null;
  }
}
