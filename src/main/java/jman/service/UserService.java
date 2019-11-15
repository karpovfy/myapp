package jman.service;

import jman.domain.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    List<User> findUsers(int currentPage, int numOfRecords);
    int getCountUser(List<User> users);
    User getUser(int id);
    User getUserByLogin(String login);
    void saveUser(User user);
    void deleteUser(User user);
}
