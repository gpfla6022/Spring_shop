package com.yhr.shop.repository;

import org.springframework.stereotype.Repository;

import com.yhr.shop.domain.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

/* 엔티티매니저팩도리를 직접 주입 받는 방법
    @PersistenceUnit
    private EntityManagerFactory emf;
 */
    /*
    회원가입
     */
    public void  join(Member member) {
        em.persist(member);
    }

    /*
    아이디로 회원조회
     */
    public Member getMemberById(Long id){
        return em.find(Member.class, id);
    }

    /*
    전체 회원 조회
     */
    public List<Member> getMembers(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
        // from의 대상이 entity 이다.
    }

    /*
    이름으로 회원 조회
     */
    public List<Member> getMembersByMemberName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }

}
