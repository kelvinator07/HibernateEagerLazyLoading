package com.geekykel;

import com.geekykel.entities.Course;
import com.geekykel.entities.Instructor;
import com.geekykel.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LazyHQL {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int id = 2;

            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("GeekyKel: Instructor: " + instructor);

            System.out.println("GeekyKel: Courses: " + instructor.getCourses());

            session.getTransaction().commit();

            session.close();

            System.out.println("\nGeekyKel: Session Now Closed");

            System.out.println("GeekyKel: Courses: " + instructor.getCourses());

            System.out.println("GeekyKel: Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            factory.close();
        }
    }
}
