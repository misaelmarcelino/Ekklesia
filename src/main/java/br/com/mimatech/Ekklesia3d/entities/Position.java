package br.com.mimatech.Ekklesia3d.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Member member;

    private String position;
    private String description;


}
