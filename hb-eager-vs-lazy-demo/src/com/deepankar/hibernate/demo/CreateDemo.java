package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

	public static void main(String[] args) 
	{
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try
		{
			//create the objects
 			/*
 			 * Instructor tempInstructor = 
 					new Instructor("Deepankar", "Pathak", "deep@mail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.deepankar.com/youtube", "Sleeping");
			*/
			
			//inserting another row
			Instructor tempInstructor =  new Instructor("Sanyam", "Pathak", "sanyam@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com/sanyam", "Mobiles");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
					
			//save the instructor
			//This will also save the details object because of CascadeType.ALL
			System.out.println("Saving instructor :: "+tempInstructor);
			
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally 
		{
			factory.close();
		}
	}

}
