package mx.edu.utez.user.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomReponse<T> {
    T data;
    boolean error;
    int statusCode;
    String messsage;
}
