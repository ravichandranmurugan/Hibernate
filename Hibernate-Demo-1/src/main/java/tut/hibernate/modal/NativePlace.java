package tut.hibernate.modal;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

//@Entity
//@Embeddable
@Data
public class NativePlace {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//
//	private Long id;
	private String district;
	
//	@OneToOne
//	private Person person;
	
}
