package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.deepankar.hibernate.demo.entity.Course;
import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

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
			
			//option 2: Hibernate query with HQL
			
			
			//get the instructor from db
			int theId=1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i " 
																	+"JOIN FETCH i.courses " 
																	+ "where i.id = :theInstructorId" , 
																	Instructor.class );
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute the query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			//get course for the instructor
 			System.out.println("Instructor : " +tempInstructor);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			//This works because we used an HQL query to retrieve the course before session was closed
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
