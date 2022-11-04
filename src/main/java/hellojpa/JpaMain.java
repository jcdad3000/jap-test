package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setName("B");
            Member member2 = new Member();
            member2.setName("C");
            em.persist(member);
            em.persist(member2);



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();

    }

}
