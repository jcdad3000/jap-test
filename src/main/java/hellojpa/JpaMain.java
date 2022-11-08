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

            Member member = new Member();
            member.setUsername("A");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("B");
            em.persist(member2);

            em.flush();
            em.clear();


            Member m1 = em.getReference(Member.class, member.getId());
            System.out.println("m1 = " + m1.getClass());
            Hibernate.initialize(m1);
            System.out.println("emf.getPersistenceUnitUtil().isLoaded(m1) = " + emf.getPersistenceUnitUtil().isLoaded(m1));


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

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }

}
