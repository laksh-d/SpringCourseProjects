package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//Create student obj 
			System.out.println("creating new student object..............");
			Student tempStudent = new Student("paul", "wall", "paul@luv2code.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student obj
			System.out.println("Saving the student....");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		
		finally {
			factory.close();
		}
	}

}
