package com.yhr.shop;

import com.yhr.shop.repository.MemberRepository;
import com.yhr.shop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.yhr.shop.domain.Member;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class MemberServiceTest {

    /*
    테스트를 위한 Service와 Repository 주입
     */
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Yoon");

        //when
        Long memberId = memberRepository.join(member);

        //then
        assertEquals(member, memberRepository.getMemberById(memberId));
    }

    @Test
    public void 중복회원예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("Yoon");

        Member member2 = new Member();
        member2.setName("Yoon");

        //when
        memberService.join(member1);

        try {
            memberService.join(member2);
        }catch(IllegalStateException e){
            return;
        }

        //then
        fail("예외가 발생해야 한다.");

    }

}