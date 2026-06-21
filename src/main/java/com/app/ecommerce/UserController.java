package com.app.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userService;
//    private List<User> userList = new ArrayList<>();

    @GetMapping
    //response entity make uniformitiy
    public ResponseEntity<List<User>> getAllUsers() {
//        HttpStatus.
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
//    @RequestMapping(value="/api/users/{id}",method=RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

//        User user=userService.fetchUser(id);
//        if(user==null){
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> CreateUsers(@RequestBody User user) {
        userService.addUsers(user);
        return ResponseEntity.ok( "user added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateUsers(@PathVariable Long id,@RequestBody User updatedUser) {
        boolean updated=userService.UpdateUsers(updatedUser, id);
        if (updated){
            return ResponseEntity.ok( "user updated successfully");

        }
        return ResponseEntity.notFound().build();
    }

}
