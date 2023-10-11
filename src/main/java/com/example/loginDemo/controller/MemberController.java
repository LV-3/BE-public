package com.example.loginDemo.controller;

import com.example.loginDemo.domain.Member;
import com.example.loginDemo.dto.JoinRequestDto;
import com.example.loginDemo.dto.LoginRequestDto;
import com.example.loginDemo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @GetMapping("/")//그냥 localhost:8080 들어오면 아래 호출해라
    public String home() {
        return "home";//templates가서 home이라는 html파일 찾아서
        // 거기가서 렌더링해가지고 화면 실행시켜라
    }

    //join
    @GetMapping("/members/new")//url에 직접 치는 게 Get방식, 그때는 얘가 매핑됨
    public String createForm() {
        return "members/createMemberForm";//members의 createMemberForm 찾음
        // viewResolver로 인해 createMemberForm.html파일이 선택되고
        // thymeleaf 템플릿 엔진이 렌더링해줌
    }

    @PostMapping("/members/new") // url이 똑같아도 form으로 데이터 넘어오면 post라서 얘가 선택됨
    public String create(MemberForm form, @RequestParam("selectGenre1") Optional selectGenre1, @RequestParam( "selectGenre2") Optional selectGenre2, @RequestParam( "selectGenre3") Optional selectGenre3) {
        JoinRequestDto joinRequestDto = new JoinRequestDto();
//        joinRequestDto.setUsername(form.getUsername());
        joinRequestDto.setEmail(form.getEmail());
        joinRequestDto.setPassword(form.getPassword());

        //제대로 들어갔는지 확인
        System.out.println("memberEmail = " + joinRequestDto.getEmail());
//        System.out.println("memberUsername = " + joinRequestDto.getUsername());
        System.out.println("memberPassword = " + joinRequestDto.getPassword());

        System.out.println(selectGenre1);
        System.out.println(selectGenre2);
        System.out.println(selectGenre3);

        selectGenre1.ifPresent(g -> joinRequestDto.setGenre1(g.toString()));
        selectGenre2.ifPresent(g -> joinRequestDto.setGenre2(g.toString()));
        selectGenre3.ifPresent(g -> joinRequestDto.setGenre3(g.toString()));


        memberService.join(joinRequestDto);

        return "home";
    }


    @GetMapping("/member/login")
    public String loginform() {
        return "members/login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute LoginRequestDto loginRequestDto, HttpSession session) {
        LoginRequestDto loginResult = memberService.login(loginRequestDto);
        if (loginResult != null) { // login의 리턴타입 Optional로 고쳐서 isNullable로 수정 고려(231005)
            session.setAttribute("loginEmail", loginResult.getEmail());
            System.out.println("로그인 성공");
            return "afterlogin";
        } else {
            System.out.println("로그인 실패");
            return "members/login"; // throw Exception으로 바꿀 수 있음(231005)
        }
    }


    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // model에 아예 모든 회원 List 만들어서 통째로 넘기기
        return "members/memberList";
    }
}
