package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create seesion
		Session session = factory.getCurrentSession();
		
		try {
			//Create 3 student obj 
			System.out.println("creating new student object..............");
			Student tempStudent1 = new Student("John", "doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "public", "mary@luv2code.com");
			Student tempStudent3 = new Student("Bonita", "applebum", "bonita");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student obj
			System.out.println("Saving the student....");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		
		finally {
			factory.close();
		}
	}

}
