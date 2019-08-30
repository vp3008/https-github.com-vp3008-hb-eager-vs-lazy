package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Course;
import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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
			//start a transaction
			session.beginTransaction();
					
			//get the instructor from db
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//get course for the instructor
 			System.out.println("Instructor : " +tempInstructor);
			
			System.out.println("Deepankar: Courses : " + tempInstructor.getCourses());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("\nSession is now closed\n");
			
			//This works because we used a getter to retrieve the course before session was closed
			System.out.println("Deepankar: Courses : " + tempInstructor.getCourses());

			
			
			System.out.println("Deepankar: Done!!");	
		}
		finally 
		{
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
