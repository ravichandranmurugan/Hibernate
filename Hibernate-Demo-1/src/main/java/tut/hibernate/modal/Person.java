package tut.hibernate.modal;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data

@NoArgsConstructor


public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
//	
//	@OneToOne( cascade = CascadeType.ALL)
//	@JoinColumn(name = "native_id")
	@Embedded
	/*
	 * @OneToOne(mappedBy = "person", cascade = CascadeType.ALL) bi-directional
	 */	private NativePlace native1;
	
}
