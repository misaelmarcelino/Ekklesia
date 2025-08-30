package br.com.mimatech.Ekklesia.entities;

import br.com.mimatech.Ekklesia.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private int age;
    private LocalDate dateOfBirth;
    private LocalDate dateOfBaptism;

    @OneToMany(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Position position;

    private String password;
    private Role role;

    private LocalDate creatAt;
    private LocalDate updateAt;

}
