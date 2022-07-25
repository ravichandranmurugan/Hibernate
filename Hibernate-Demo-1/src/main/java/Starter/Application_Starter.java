package Starter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tut.hibernate.modal.Person;

public class Application_Starter {

	public static void main(String[] args) {

		EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("tut.hibernate.modal");
		EntityManager entityManager = entityMangerFactory.createEntityManager();
		EntityTransaction entityTranscation = entityManager.getTransaction();
		entityTranscation.begin();
		Person p = new Person((long) 1,"ravi","chandran");
		entityManager.persist(p);
		entityTranscation.commit();
		entityManager.close();
		
	}

}
  