package br.com.mimatech.Ekklesia3d.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String position;

    @Column(length = 255)
    private String description;

}
