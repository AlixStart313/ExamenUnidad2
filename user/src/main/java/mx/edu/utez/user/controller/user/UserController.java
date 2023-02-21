package mx.edu.utez.user.controller.user;


import mx.edu.utez.user.controller.user.dtos.UserDTO;
import mx.edu.utez.user.model.user.UserModel;
import mx.edu.utez.user.service.user.UserService;
import mx.edu.utez.user.utils.CustomReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-users/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<CustomReponse<List<UserModel>>> getAll(){
        return  new ResponseEntity<>(
                this.userService.getAll(),HttpStatus.OK
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomReponse<UserModel>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.userService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomReponse<UserModel>> saveUser(@RequestBody UserDTO userDTO,@Valid BindingResult result){
        if(result.hasErrors()){
            return  new ResponseEntity<>(
                    null, HttpStatus.BAD_REQUEST
            );
        }

        return  new ResponseEntity<>(
                this.userService.insert(userDTO.getUser()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomReponse<UserModel>> updateCategory(@RequestBody UserDTO userDTO, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.userService.update(userDTO.getUser()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomReponse<Integer>> enableOrDisable(
            @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                this.userService.changeStatus(userDTO.changeStatus()),
                HttpStatus.OK
        );
    }
}
