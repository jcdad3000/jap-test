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
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloC");

//            //비영속 상태
//            Member member1 = new Member(150L,"A");
//            Member member2 = new Member(151L,"B");
//            member.setId(101L);
//            member.setName("HEllo JPA2");
//
//            //영속
//
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("AAAAA");
            em.detach(findMember);

            //Member member = new Member(200L,"member200");
            //em.persist(member);
            //em.flush();



            System.out.println("========================");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();

    }

}
