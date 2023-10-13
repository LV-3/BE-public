package com.example.VodReco.controller;

import com.example.VodReco.domain.Member;
import com.example.VodReco.dto.JoinRequestDto;
import com.example.VodReco.dto.LoginRequestDto;
import com.example.VodReco.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired//스프링 컨테이너가 뜰 때 MemberController 생성함 -> 그때 생성자도 호출되는데
    //Autowired가 붙어있으면 스프링이 스프링컨테이너에 있는 memberSerice객체를 가져다 연결시켜줌
    //Controller와 Service 연결해준단 뜻
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //이 생성자 쓰는 대신 클래스 위에 @RequiredArgsConstructor 써도 됨
        //@Service 없이 이렇게 쓰면 스프링이 memberService를 못 찾는 에러남
        //memberService는 아무 어노테이션 없는 순수 자바 코드
        //(<->MemberController처럼 Controller어노테이션같은 거 있으면
        //스프링이 어 이건 내가 관리하는 애구나. 라고 알게 되는데)
        //memberService같은 순수 자바 클래스는 스프링이 아무것도 알 수가 없음
        //그떄 MemberService 선언부 위에 @Service 추가
    }

    //메인페이지 -> 바로 로그인으로 연결
    @GetMapping("/")//그냥 localhost:8080 들어오면 아래 호출해라
    public String home() {
        return "home";
    }




    //join
    @GetMapping("/members/new")//url에 직접 치는 게 Get방식, 그때는 얘가 매핑됨
    public String createForm() {
        return "members/createMemberForm";//members의 createMemberForm 찾음
        // viewResolver로 인해 createMemberForm.html파일이 선택되고
        // thymeleaf 템플릿 엔진이 렌더링해줌
    }

    @PostMapping("/members/new") // url이 똑같아도 form으로 데이터 넘어오면 post라서 얘가 선택됨
    public String create(@Valid MemberForm form, @RequestParam("selectGenre1") Optional<Integer> selectGenre1, @RequestParam( "selectGenre2") Optional<Integer> selectGenre2, @RequestParam( "selectGenre3") Optional<Integer> selectGenre3, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("회원가입 실패");
            //유효성 검사 제대로 되나 찍어보고 싶은데 html자체 기능으로 email 검사해줘서 회원가입 버튼이 안 눌림(231012)
            return "redirect:/"; //231012 이거 안됨ㅎ
        } else {
            JoinRequestDto joinRequestDto = new JoinRequestDto();
            joinRequestDto.setEmail(form.getEmail());
            joinRequestDto.setPassword(form.getPassword());

            //제대로 들어갔는지 확인
            System.out.println("memberEmail = " + joinRequestDto.getEmail());
            System.out.println("memberPassword = " + joinRequestDto.getPassword());

            System.out.println(selectGenre1);
            System.out.println(selectGenre2);
            System.out.println(selectGenre3);

            //선택되지 않은 장르는 null
            // 이거 0으로 바꾸기! (231012 회의)
//            selectGenre1.ifPresent(g -> joinRequestDto.setGenre1(g.toString()));
//            selectGenre2.ifPresent(g -> joinRequestDto.setGenre2(g.toString()));
//            selectGenre3.ifPresent(g -> joinRequestDto.setGenre3(g.toString()));

            //JoinRequestDTO에서 0으로 초기화된 상태
            selectGenre1.ifPresent(g -> joinRequestDto.setGenre1(1));
            selectGenre2.ifPresent(g -> joinRequestDto.setGenre2(1));
            selectGenre3.ifPresent(g -> joinRequestDto.setGenre3(1));
            Optional<Integer>[] optionals = new Optional[]{selectGenre1, selectGenre2, selectGenre3};

            // 장르 2개 이상 선택
            if (this.memberService.validateGenres(optionals)) {
                memberService.join(joinRequestDto);
                return "redirect:/member/login";
            }
                System.out.println("장르를 2개 이상 선택하세요");
                return "redirect:/members/new";
        }
    }



    //login
    @GetMapping("/member/login")
    public String loginform() {
        return "members/login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute LoginRequestDto loginRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(-1);//세션 유지시간 무한대
        //로그아웃 시 invalidate() 이용

        LoginRequestDto loginResult = memberService.login(loginRequestDto);
        if (loginResult != null) { // login의 리턴타입 Optional로 고쳐서 isNullable로 수정 고려(231005)
            session.setAttribute("loginEmail", loginResult.getEmail());

            System.out.println("로그인 성공");
            return "redirect:/movies/posters";
        } else {
            System.out.println("로그인 실패");
            return "members/login"; // throw Exception으로 바꿀 수 있음(231005)
        }
    }






    //회원 목록 보기 기능(추후 삭제할 것)
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // model에 아예 모든 회원 List 만들어서 통째로 넘기기
        return "members/memberList";
    }
}
