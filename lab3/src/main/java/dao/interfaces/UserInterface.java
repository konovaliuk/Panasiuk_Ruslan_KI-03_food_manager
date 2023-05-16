package dao.interfaces;

import entities.User;
import java.util.List;
import java.util.Optional;

public interface UserInterface {
    Optional<User> findUserById(Long id);
    Optional<User> findUser(String username);
    List<User> findAllUser();
    boolean addUser(User user);
    boolean updateUser(User user);
}
