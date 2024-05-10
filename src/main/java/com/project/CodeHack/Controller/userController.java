package com.project.CodeHack.Controller;

import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserServiceImpl userService;
//    @GetMapping
//    public ResponseEntity<List<UserDto>> getUserList(){
//
//         userService.getAllUsers();
//
//
//
//   }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDt0=userService.registerUser(userDto);
        return new ResponseEntity<>(userDt0,HttpStatus.CREATED);


    }
@DeleteMapping("/{userID}")
    public String Delete(@PathVariable String userID){

//        UserDto userDto=userService.deleteUser(userID);
//    System.out.println(userDto.toString());
//        return ResponseEntity.ok(userDto);
    return  "working";


}
}


//GET /users - Retrieve a list of all registered users
//GET /users/{userId} - Retrieve the details of a specific user
//POST /users - Register a new user to the contest
//PUT /users/{userId} - Update the score of a specific user
//DELETE /users/{userId} - Deregister a specific user from the contest
