package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Course;
import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;


public class CreateInstructorDemo {

	public static void main(String[] args) 
	{
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try
		{
			//create the objects
 			Instructor tempInstructor =  
 											new Instructor("Susan", "Public", "susan.public@gmail.com");
			InstructorDetail tempInstructorDetail = 
											new InstructorDetail("www.youtube.com/sanyam", "Video Games");
			
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
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
