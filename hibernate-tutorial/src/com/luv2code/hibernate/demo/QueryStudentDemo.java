package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// Create session fa ctory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create seesion
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// create query
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			System.out.println("\nall students from db \n");
			displayStudents(theStudents);

			// query students: lastName : doe
			System.out.println("\n students whose lastName is doe");
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			displayStudents(theStudents);
			
			//lastNAme ="doe" OR firstName = "daffy"
			System.out.println("\n students whose lastName is doe OR firsName is Daffy \n");
			theStudents = session.createQuery("from Student s "
					+ "where s.lastName = 'doe' OR firstName = 'Daffy'").getResultList();
			displayStudents(theStudents);
			
			//email ends with luv2code.co
			System.out.println("\n students whose email ends with luv2code.com\n");
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%luv2code.com'").getResultList();
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}

		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
