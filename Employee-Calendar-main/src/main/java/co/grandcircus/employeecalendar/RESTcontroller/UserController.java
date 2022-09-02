package co.grandcircus.employeecalendar.RESTcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import co.grandcircus.employeecalendar.exception.ItemExistsException;
import co.grandcircus.employeecalendar.exception.ItemNotFoundException;
import co.grandcircus.employeecalendar.model.User;
import co.grandcircus.employeecalendar.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    // return a list of all users can use this action for tsting.
    @GetMapping("/allUsers")
    public List<User> readAll() {

        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User readOne(@PathVariable("id") String id) {
        return userRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @ResponseBody
    @ExceptionHandler(ItemExistsException.class)
    @ResponseStatus(HttpStatus.FOUND)
    String itemNotFoundException(ItemExistsException ex) {
        return ex.getMessage();
    }

    // CR(U)D -- Update Put Completely replaces the resource. All elements of the body need to be
    // included
    @PutMapping("/user/{id}")
    public User updateComplete(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        return userRepo.save(user);
    }

    // not sure we need a patch method in the UserAPI add if use case arises.

    @PatchMapping("/user/{id}/updateadmin")
    public User updatePartial(@PathVariable("id") String id,
            @RequestParam(required = false) Boolean adminStatus) {
        User user = userRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        user.setAdminStatus(adminStatus);
        userRepo.save(user);
        return user;
    }

    // CRU(D) -- Delete
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        userRepo.deleteById(id);
    }



    // // check if user exists, and create user or return error "user Exists select other usern
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User newUser) {
        if (userRepo.findByUserName(newUser.getUserName()) == null) {
            userRepo.save(newUser);
            return newUser;
        } else {
            throw new ItemExistsException(newUser.getUserName());
        }
    }



    // @RequestBody(required = true) String userName,
    // @RequestBody(required = true) String firstName,
    // @RequestBody(required = true) String lastName,
    // @RequestBody(required = true) String dateOfBirth, Model model) {

    // // EmailValidator validator = EmailValidator.getInstance();
    // // if (validator.isValid(userName)) {
    // User newUser = userRepo.findByUserName(userName).orElseThrow(()-> new
    // ItemExistsException(userName));

    // // }
    // }


}


