package tut.hibernate.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Person {
	
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	
}
