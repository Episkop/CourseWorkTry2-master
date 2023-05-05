package ua.studert.coursework.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.studert.coursework.Entity.UserEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Model.UserModel;
import ua.studert.coursework.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String loginPage() {
        return "start";
    }

    //
//    @GetMapping("/login")
//    public String register() {
//        return "login";
//    }


//    @PostMapping("/registration")
//    public ResponseEntity registration(@RequestBody UserEntity userEntity) {
//        try {
//            userService.registration(userEntity);
//            return ResponseEntity.ok("User have saved!");
//        } catch (AlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
//        }
//    }
}

