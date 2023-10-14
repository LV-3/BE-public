package com.example.VodReco.service;

import com.example.VodReco.domain.Member;
import com.example.VodReco.dto.JoinRequestDto;
import com.example.VodReco.dto.LoginRequestDto;
import com.example.VodReco.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@RequiredArgsConstructor // 확인 필요(231005)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        //memberRepository를 직접 여기서 생성하는 게 아니라
        //외부에서 넣어주도록 코드 바꿔쥬기(???? 확인 필요 10.5)

        //MemberRepository인터페이스의 구현체인 MemoryMemberRepository를
        //스프링컨테이너에서 꺼내서 Service에 주입해줌<이거 강의 필긴데,,,뭔소리더라
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */

    //리턴 void로 수정(231014)
    public void join(JoinRequestDto joinRequestDto){
        // 같은 이름이 있는 중복 회원은 X
        validateDuplicateMember(joinRequestDto);
        //Ctrl+Alt+V가 리턴 만들어주는 단축키
        System.out.println("joinDto 제대로 들어오는지 확인 =" + joinRequestDto.getEmail()); // 여기까지는 나옴 -> toMemberEntity가 문제다
        System.out.println("이메일 나오나 확인 =" + joinRequestDto.toMemberEntity().getEmail());
        memberRepository.save(joinRequestDto.toMemberEntity());
    }

    // 같은 이메일 가진 중복회원은 안 받겠다
    private void validateDuplicateMember(JoinRequestDto joinRequestDto) { // 중복 회원 검증
        memberRepository.findByEmail(joinRequestDto.getEmail()) // Optional member 나옴
                .ifPresent(m -> { //result가 null이 아니면 이하 로직 동작(ifPresent는 Optional이라 가능한 것)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });//추후 예외용 페이지 만들어야 함(231005)
    }


    //장르 2개 이상 선택
    public boolean validateGenres(Optional<Integer>[] optionals) {
        int count = 0;
        for (Optional<Integer> optional : optionals){
            if (optional.isPresent()) {
                count++;
            }
        }
        return (count >= 2);
    }


    //로그인
    public LoginRequestDto login(LoginRequestDto loginRequestDto) {
        Optional<Member> byEmail = memberRepository.findByEmail(loginRequestDto.getEmail());
        if (byEmail.isPresent()) {

            if (byEmail.get().getPassword().equals(loginRequestDto.getPassword())) {
                 return loginRequestDto;

            } else {
                return null; // throw Exception으로 변경 고려(231005), 패스워드 불일치하는 경우
            }
        }else{
            return null; // email로 조회 안 됨(회원가입 안 되어있는 경우)
        }
    }



    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Integer memberId) {
        return memberRepository.findById(memberId); // id들어오면 저장소(memberRepository 내의 store)에서 찾아서
        //있으면 해당하는 member optional로 싸서 리턴
        // optional 사용이유: isPresent, isempty, isNullable 등 쓰려고
    }
}
