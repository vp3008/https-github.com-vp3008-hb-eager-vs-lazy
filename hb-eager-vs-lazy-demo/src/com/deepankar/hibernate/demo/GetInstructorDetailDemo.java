package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;


public class GetInstructorDetailDemo {

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

			//start a transaction
			session.beginTransaction();
			
			//get the instructor details object
			int theId = 2; 
			InstructorDetail instructorDetail = session.get(InstructorDetail.class , theId);
			
			//print the instructor detail
			System.out.println("InstructorDetail ::" + instructorDetail);
			
			//print the associated instructor
			System.out.println("Associated instructor ::" + instructorDetail.getInstructor());
				
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			session.close();
			factory.close();
		}
	}

}
