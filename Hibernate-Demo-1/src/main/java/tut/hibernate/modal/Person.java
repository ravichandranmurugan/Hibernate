package tut.hibernate.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Person {
	
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	
}
