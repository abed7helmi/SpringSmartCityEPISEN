package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.User;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/User")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getAll")
    public Iterable<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") final Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("{userId}")
    public void updateUser(@RequestBody User user,
                                @PathVariable("userId") int userId){
        userService.updateUser(userId, user.getUser_name());
    }

    @GetMapping("/checkUser")
    public Boolean checkUser(@RequestParam("name") String name) {
        Integer verif = userService.checkName(name);
        if (verif == 1) {
            return true;
        } else {
            return false;
        }
    }
}
