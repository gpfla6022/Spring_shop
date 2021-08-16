package com.yhr.shop;

import com.yhr.shop.domain.Member;
import com.yhr.shop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // JPA의 transaction의 옵션으로 readOnly를 부여하면, 조회에서 성능이 최적된다.
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */
    // LOMBOK을(@AllArgsConstructor ) 이용한다면 Autowired를 제외하고 사용할 수 있다.
    // @RequiredArgsContsructor 를 사용하면 필요한 필드를 갖고있는 생성자를 생성해준다.
    // Spring 에서 필드가 하나있고 Autowired가 유추 가능하다면 따로 명시하지 않아도 자동으로 autowired를 적용한다.
    // 따라서 하기의 코드에서 @Autowired를 제외하여도 무방하다.

    /*
    회원가입
     */
    @Transactional(readOnly = false) // 따로 옵션을 넣어주면 우선순위가 된다.
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원검증
        memberRepository.join(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.getMembersByMemberName(member.getName());
        if(!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    // 조회에는 transaction의 옵션으로 readOnly를 부여하는 것이 좋다.
    // 가입및 수정에는 readOnly를 부여하지 않아야 한다.(데이터 변경 불가능)
    
    /*
    회원 전체 조회
     */
    public List<Member> getMembers(){
        return memberRepository.getMembers();
    }

    /*
    아이디로 회원 조회
     */
    public Member getMemberById(Long id){
        return memberRepository.getMemberById(id);
    }


}
