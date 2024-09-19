package com.farukgenc.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String username;

	private String password;

	private String email;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Sala> salas;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Reserva> reserva;
}
