package com.geekykel;

import com.geekykel.entities.Course;
import com.geekykel.entities.Instructor;
import com.geekykel.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class LazyHQL2 {

    public static void main(String[] args) {

        String QUERY = "select i from Instructor i JOIN FETCH i.courses where i.id=:instructorId";

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

            // option 2  Hibernate with HQL

            int id = 2;

            Query<Instructor> query = session.createQuery(QUERY, Instructor.class);

            query.setParameter("instructorId", id);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("GeekyKel: Instructor: " + instructor);

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
