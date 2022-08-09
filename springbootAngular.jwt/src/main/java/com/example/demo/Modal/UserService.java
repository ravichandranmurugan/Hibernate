package com.example.demo.Modal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String ingUrl;
	private LocalDate lastLoginDate;
	private LocalDate joinDate;
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> role = new ArrayList<Role>();
	private boolean isActive;
	private boolean isNotLocked;
	
}
