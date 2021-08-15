package com.yhr.shop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /*
    회원가입
     */
    public Long join(Member member){
        // member객체 영속화
        em.persist(member);

        // member 인스턴스에서 Id값을 리턴
        return member.getId();
    }

    /*
    회원조회
     */
    public Member find(Long id){
        // 엔티티 매니저로 id값으로 DB에서 데이터 조회 후
        // Member인스턴스를 반환
        return em.find(Member.class, id);
    }

}
