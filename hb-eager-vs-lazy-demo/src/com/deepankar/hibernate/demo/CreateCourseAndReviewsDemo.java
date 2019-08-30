package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Course;
import com.deepankar.hibernate.demo.entity.Instructor;
import com.deepankar.hibernate.demo.entity.InstructorDetail;
import com.deepankar.hibernate.demo.entity.Review;


public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) 
	{
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try
		{
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse = new Course("Pacman - How to score One Million Points");
			
			//add some reviews
			tempCourse.add(new Review("Great Course.. Loved it"));
			tempCourse.add(new Review("Cool Course.. Job Well done"));
			tempCourse.add(new Review("Positive review"));
			tempCourse.add(new Review("Dumb Course"));
			
			//save the course ..  and leverage the cascade all
			session.save(tempCourse);
			
			
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
