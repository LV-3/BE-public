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

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


//    1. View

    //join
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
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


