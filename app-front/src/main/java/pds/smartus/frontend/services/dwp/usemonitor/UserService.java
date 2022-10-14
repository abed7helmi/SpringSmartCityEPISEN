package pds.smartus.frontend.services.dwp.usemonitor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.usemonitor.User;
import pds.smartus.frontend.repositories.dwp.usemonitor.UserProxy;

@Data
@Service
public class UserService {
    @Autowired
    private UserProxy userProxy;

    /*public User getUserById(final int userId) {
        return userProxy.getUserById(userId);
    }*/

    public Boolean checkUser(String name) {
        return userProxy.CheckUser(name);
    }
}
