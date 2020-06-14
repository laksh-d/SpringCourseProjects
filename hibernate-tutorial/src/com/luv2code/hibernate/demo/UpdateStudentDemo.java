package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create seesion
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// find out new student's id " primary key
			System.out.println("saved student id:" +studentId);
			
			//get new sesion and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrive student based on the id: 
			System.out.println("getting student id with:"+studentId);
			
			Student myStudent = session.get(Student.class,studentId);
			
			System.out.println("updating student...."); 
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("\n updating email for all students... \n");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		
		finally {
			factory.close();
		}
	}

}
