package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.User;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).get();
    }

    public void deleteUser(final Integer id) {
        userRepository.deleteById(id);
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public Integer checkName(String name) {
        if(userRepository.checkName(name)==null) {
            return 0;
        } else {
            return userRepository.checkName(name);
        }
    }

    @Transactional
    public void updateUser(int userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id" + userId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(user.getUser_name(), name)){
            user.setUser_name(name);
        }

    }

}
