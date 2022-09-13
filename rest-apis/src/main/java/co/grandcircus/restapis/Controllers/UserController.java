package co.grandcircus.restapis.Controllers;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.restapis.Exceptions.EmailNotFoundException;
import co.grandcircus.restapis.Exceptions.InvalidEmailException;
import co.grandcircus.restapis.Exceptions.ItemNotFoundException;
import co.grandcircus.restapis.Exceptions.UserNameExistsException;
import co.grandcircus.restapis.Models.User;
import co.grandcircus.restapis.Repositories.UserRepository;



@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    // return a list of all users can use this action for tsting.
    @GetMapping("/users")
    public List<User> readAll() {

        return userRepo.findAll();
    }



    @GetMapping("/users/{id}")
    public User readById(@PathVariable("id") String id) {
        return userRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping("/userprofile")
    public User readByUserName(@RequestParam String userName) {
        return userRepo.findByUserName(userName).orElseThrow(() -> new EmailNotFoundException(userName));
    }


    // Update Put Completely replaces the resource. All elements of the
    // body need to be
    // included
    @PutMapping("/users/{id}/change")
    public User updateComplete(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        return userRepo.save(user);
    }

    // not sure we need a patch method in the UserAPI add if use case arises.

    @PatchMapping("/users/{id}/updateadmin")
    public User updatePartial(@PathVariable("id") String id,
            @RequestParam(required = false) Boolean adminStatus) {
        User user = userRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        user.setAdminStatus(adminStatus);
        userRepo.save(user);
        return user;
    }

    // CRU(D) -- Delete
    @DeleteMapping("/users/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        userRepo.deleteById(id);
    }

    // check if user exists, and create user or return error "user Exists select
    // other usern
    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User newUser) {
        EmailValidator validator = EmailValidator.getInstance();

        if (validator.isValid(newUser.getUserName())) {
            if (!userRepo.findByUserName(newUser.getUserName()).isPresent()) {
                userRepo.save(newUser);
                return newUser;
            } else {
                throw new UserNameExistsException(newUser.getUserName());
            }
        }
        else{
         throw new InvalidEmailException(newUser.getUserName());
        }
    }


}
