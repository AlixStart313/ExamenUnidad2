package mx.edu.utez.user.controller.user.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.user.model.user.UserModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private long id;
    @NotNull
    @NotBlank
    @Length(min = 3, max=120)
    private  String  username;
    private  String password;
    private  String name;
    private  String surname;
    private  String lastname;
    private  String curp;
    private  String bDay;
    private  boolean status;

    public UserModel getUser(){
        return new UserModel(getId(),
        getUsername(),
        getPassword(),
        getName(),
        getSurname(),
        getLastname(),
        getCurp(),
        getBDay(),
        isStatus());


    }

    public UserModel changeStatus() {
        return  new UserModel(
                getId(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                isStatus()

        );
    }


}
