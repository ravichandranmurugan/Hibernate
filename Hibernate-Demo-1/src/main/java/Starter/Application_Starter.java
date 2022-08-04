package Starter;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tut.hibernate.modal.Person;

public class Application_Starter {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {

		EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("tut.hibernate.modal");
		EntityManager entityManager = entityMangerFactory.createEntityManager();
		EntityTransaction entityTranscation = entityManager.getTransaction();
		entityTranscation.begin();
		Person p = new Person();
		p.setFirstName("Ravi");
		p.setLastName("Chandran");
		entityManager.persist(p);
		entityTranscation.commit();
		Query query =entityManager.createQuery("from Person");
		List<Person> list = query.getResultList();
		Iterator<Person> ir = list.iterator();
		for (Person person : list) {
			System.out.println(person.getFirstName());
		}
		System.out.println();
		entityManager.close();

	}

}
  