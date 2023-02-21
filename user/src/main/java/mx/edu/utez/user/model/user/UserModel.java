package mx.edu.utez.user.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 120)
    private String username;

    @Column(length = 20)
    private String password;

    @Column(length = 150)
    private String name;

    @Column(length = 150)
    private String surName;

    @Column( length = 150)
    private String lastName;

    @Column(unique = true, length = 20)
    private String  curp;

    @Column( length = 12)
    private String bDay;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private boolean status;



}
