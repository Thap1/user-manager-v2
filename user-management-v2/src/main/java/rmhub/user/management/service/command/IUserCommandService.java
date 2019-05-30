package rmhub.user.management.service.command;

import rmhub.user.management.model.User;

public interface IUserCommandService {
  User createUser(User newUser);
  User updateUser(String name, String role, String engineeringBureau);
  User deleteByName(String name);
  String changePassword(int id, String currentPass, String newPass, String confirmPass);
  String resetPassword(String email);
  boolean valiadteUpdateUser(String name, String role, String engineeringBureau);
  boolean valiateNewUser(User newUser);
//  boolean validateEmailAddress(String emailAddress);
  boolean validateDeleteByName(String name);
}
