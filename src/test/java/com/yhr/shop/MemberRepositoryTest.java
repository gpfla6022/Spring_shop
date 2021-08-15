package com.yhr.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 스프링 부트의 테스트를 이용
class MemberRepositoryTest {

    //실제 프로젝트에서 MemberRepository를 주입받은 테스트 생성
    @Autowired MemberRepository memberRepository;

    /*

    */
    @Test
    @Transactional //DB에 넣은 데이터가 안나오는 이유는 test케이스에 transactional을 넣으면 rollback이 되기 때문이다.
    @Rollback(false) // 그래서 다음과 같은 어노테이션을 넣어준다.
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("에디");

        //when
        // memberRepository.join(); 후 ctrl+alt+v -> 변수추출
        Long memberId = memberRepository.join(member);
        Member findMember = memberRepository.find(memberId);

        //then
        Assertions.assertEquals(findMember.getId(), member.getId());
        Assertions.assertEquals(findMember, member);

    }

}