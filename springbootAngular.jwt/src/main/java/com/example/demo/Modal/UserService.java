package com.example.demo.Modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(nullable = false, updatable = false)
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String imgUrl;
	private Date lastLoginDate;
	private Date lastLoginDateDispaly;
	private Date joinDate;
//	@JsonIgnore
//	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private String role;
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;

}
