package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create seesion
		Session session = factory.getCurrentSession();

		try {

			int studentId = 1;

			// find out new student's id " primary key
			System.out.println("saved student id:" + studentId);

			// get new sesion and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrive student based on the id:
			System.out.println("getting student id with:" + studentId);

			Student myStudent = session.get(Student.class, studentId);

			// delete the student
//			System.out.println("Deleting student:" + myStudent);
//			session.delete(myStudent);

			// delete where id = 2'
			System.out.println("\n deleting student id = 2 \n");
			session.createQuery("delete from Student where id=2").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			// NEW CODE

			System.out.println("Done!");
		}

		finally {
			factory.close();
		}
	}

}
