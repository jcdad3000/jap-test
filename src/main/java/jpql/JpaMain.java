package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            em.persist(member1);


            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age=20").executeUpdate();
//            String query = "select m from Member m where m.id = :memberId";
//            Member findMEmber = em.createQuery(query, Member.class).setParameter("memberId",member1.getId()).getSingleResult();

            //System.out.println("findMEmber = " + findMEmber);
//            System.out.println("resultCount = " + resultCount);
//            em.flush();
//            em.clear();
//            Member newMember = em.find(Member.class,member1.getId());
//
//            System.out.println("member1 = " + newMember.getAge());
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
