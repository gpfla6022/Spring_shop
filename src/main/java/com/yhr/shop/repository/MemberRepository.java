package com.yhr.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.yhr.shop.domain.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    /*
        JPA 에서 제공하는 표준 어노테이션
        EntityManager를 사용할 수 있다.
        @PersistenceContext를 사용하여 컨테이너에서 엔티티매니저를 주입받을 수 있다.
         */
        //@PersistenceContext
        private final EntityManager em;

        // 엔티티 매니저를 Autowired로도 주입받을 수 있다. -> 생성자로 자동으로 주입받을 수 있다.
        // 마찬가지로 @RequiredArgsConstructor 를 사용할 수 있다.
        /*
        @Autowired
        public MemberRepository(EntityManager em) {
            this.em = em;
        }
        */

/* 엔티티매니저팩도리를 직접 주입 받는 방법
    @PersistenceUnit
    private EntityManagerFactory emf;
 */
    /*
    회원가입
     */
    public Long  join(Member member) {
        em.persist(member);

        return member.getId();
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
