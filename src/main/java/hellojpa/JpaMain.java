package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            System.out.println("=============================");

            Address address = new Address("city", "street", "zipcode");

            Member member1 = new Member();
            member1.setUsername("m1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("m2");
            member2.setHomeAddress(address);
            em.persist(member2);

            member1.getHomeAddress().setCity("Seoul");


            System.out.println("=============================");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

        emf.close();

    }



}
