package com.luv2code.hibernate.demo.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CRUDDemo {

	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// create session //testing comment
		Session session = factory.getCurrentSession();

		try {
			// Create Employee obj
			Employee tempEmployee = new Employee("laksh", "devil", "google");

			// start a transaction
			session.beginTransaction();

			// save the student obj
			session.save(tempEmployee);

			// commit transaction
			session.getTransaction().commit();

			// READ code

			// find out new Employee id " primary key
			System.out.println("saved student id:" + tempEmployee.getId());

			// get new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// Retrieve student based on the id: primary

			List<Employee> myStudent = session.createQuery("from Employee").getResultList();

			displayStudents(myStudent);

			// commit the transaction
			session.getTransaction().commit();

			// UPDATE code

			// get new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			session.createQuery("update Employee set company='google' where company = 'digit'").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			// DELETE code

			// get new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("delete from Employee where lastName = 'devil'").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}

		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Employee> theEmployee) {
		for (Employee tempStudent : theEmployee) {
			System.out.println(tempStudent);
		}
	}

}
