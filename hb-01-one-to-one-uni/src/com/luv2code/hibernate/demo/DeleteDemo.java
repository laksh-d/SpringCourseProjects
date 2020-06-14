package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor by primary key / id
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found instructot : " + tempInstructor);
			
			
			
			//delete the instructor
			if(null != tempInstructor) {
				
				System.out.println("deleting : " + tempInstructor);
				
				//note: will also delete associated "details" object
				//because of CascadeType.ALL
				//
				session.delete(tempInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		
		finally {
			factory.close();
		}
	}

}
