package Starter;


import java.util.Iterator;
import java.util.List;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tut.hibernate.modal.Student;

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

//		EntityManager entityManager = entityMangerFactory.createEntityManager();
//		EntityTransaction entityTranscation = entityManager.getTransaction();
//		entityTranscation.begin();
//		Person p = new Person();
//		p.setName("Ravi");
//
//		NativePlace n = new NativePlace();
//		p.setNative1(n);
//		n.setDistrict("TPR");
//		n.setPerson(p);
//		
//		
//		
//		entityManager.persist(p);
		
//		Query q = entityManager.createQuery("from NativePlace");
//		List<NativePlace> l = q.getResultList();
//		
//		for (NativePlace nativePlace : l) {
//           Person pp = nativePlace.getPerson();
//           System.out.println(pp.getName());
//		}
//		
//		
//		Query q1 = entityManager.createQuery("from Person");
//		List<Person> l1 = q1.getResultList();
//		
//		for (Person person : l1) {
//          NativePlace np= person.getNative1();
//          System.out.println(np.getDistrict());
//		}
//		
//		entityTranscation.commit();
//		entityManager.close();
		  EntityManager em = entityMangerFactory.createEntityManager();
	        em.getTransaction().begin();
//
//	        Account checking = new Checking(300.00, 10.00);
//	        em.persist(checking);
//
//	        Account savings = new Savings(5000.00, 800.00);
//	        em.persist(savings);

		  Student s1 = new Student("SNO.1", "Tina", "Xing", "Computer Science", LocalDate.of(2019, 11, 29));
	        Student s2 = new Student("SNO.2", "Tina2", "Xing2", "Arts", LocalDate.of(2018, 10, 19));
	        em.persist(s1);
	        em.persist(s2);
		  
	        em.getTransaction().commit();
	        em.close();
		entityMangerFactory.close();
		

	}

}
  