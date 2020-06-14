package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create seesion
		Session session = factory.getCurrentSession();
		
		try {
			//Create student obj 
			System.out.println("creating new student object..............");
			Student tempStudent = new Student("Daffy", "Duck", "Daffy@luv2code.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student obj
			System.out.println("Saving the student...."+tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			//MY NEW CODE
			
			// find out new student's id " primary key
			System.out.println("saved student id:" +tempStudent.getId());
			
			//get new sesion and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrive student based on the id: pri
			System.out.println("getting student id with:"+tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("get complete:"+myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		
		finally {
			factory.close();
		}
	}

}
