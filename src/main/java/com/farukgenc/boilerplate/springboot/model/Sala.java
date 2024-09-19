package com.farukgenc.boilerplate.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sala")
@Getter
@Setter
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 3)
    private Integer capacidad;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Boolean estado;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User user;
}


