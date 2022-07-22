package Starter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application_Starter {

	public static void main(String[] args) {

		EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("tut.hibernate.modal");
		EntityManager entityManager = entityMangerFactory.createEntityManager();
		
	}

}
  