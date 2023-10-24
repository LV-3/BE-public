package com.example.VodReco.controller;

import com.example.VodReco.domain.Member;
import com.example.VodReco.dto.JoinRequestDto;
import com.example.VodReco.dto.LoginRequestDto;
import com.example.VodReco.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/members")
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;

    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }


    //join

//    @PostMapping("/new") // url이 똑같아도 form으로 데이터 넘어오면 post라서 얘가 선택됨
//    public JoinRequestDto create(@RequestBody MemberForm form, @RequestParam("selectGenre1") Optional<Integer> selectGenre1, @RequestParam("selectGenre2") Optional<Integer> selectGenre2, @RequestParam("selectGenre3") Optional<Integer> selectGenre3) {
//        JoinRequestDto joinRequestDto = new JoinRequestDto();
//        joinRequestDto.setEmail(form.getEmail());
//        joinRequestDto.setPassword(form.getPassword());
//
//        //제대로 들어갔는지 확인
//        System.out.println("memberEmail = " + joinRequestDto.getEmail());
//        System.out.println("memberPassword = " + joinRequestDto.getPassword());
//
//        System.out.println(selectGenre1);
//        System.out.println(selectGenre2);
//        System.out.println(selectGenre3);
//
//        //선택되지 않은 장르는 null
////            selectGenre1.ifPresent(g -> joinRequestDto.setGenre1(g.toString()));
////            selectGenre2.ifPresent(g -> joinRequestDto.setGenre2(g.toString()));
////            selectGenre3.ifPresent(g -> joinRequestDto.setGenre3(g.toString()));
//
//        //JoinRequestDTO에서 0으로 초기화된 상태
//        selectGenre1.ifPresent(g -> joinRequestDto.setGenre1(1));
//        selectGenre2.ifPresent(g -> joinRequestDto.setGenre2(1));
//        selectGenre3.ifPresent(g -> joinRequestDto.setGenre3(1));
//        Optional<Integer>[] optionals = new Optional[]{selectGenre1, selectGenre2, selectGenre3};
//
//        // 장르 2개 이상 선택
//        if (this.memberServiceImpl.validateGenres(optionals)) {
//            memberServiceImpl.join(joinRequestDto);
//            //API 테스트용 리턴
//            return joinRequestDto;
//        }
//        System.out.println("장르를 2개 이상 선택하세요");
//        return null;
//    }


    @PostMapping("/new") // url이 똑같아도 form으로 데이터 넘어오면 post라서 얘가 선택됨
    public Member create(@RequestBody MemberForm form) {
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setEmail(form.getEmail());
        joinRequestDto.setPassword(form.getPassword());
        joinRequestDto.setSelectedVods(form.getSelectedVods());


        // 장르 2개 이상 선택
//        if (this.memberServiceImpl.validateGenres(optionals)) {
        memberServiceImpl.join(joinRequestDto);
        //API 테스트용 리턴
        return memberServiceImpl.findOne(joinRequestDto.getEmail()).get();
        }
//        else{return null;}
    }






    //login(보류)

//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginRequestDto loginRequestDto, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(-1);//세션 유지시간 무한대
//        //로그아웃 시 invalidate() 이용
//
//        LoginRequestDto loginResult = memberService.login(loginRequestDto);
//        if (loginResult != null) { // login의 리턴타입 Optional로 고쳐서 isNullable로 수정 고려(231005)
//            session.setAttribute("loginEmail", loginResult.getEmail());
//
//            System.out.println("로그인 성공");
//            return "redirect:/movies/posters";
//        } else {
//            System.out.println("로그인 실패");
//            return "members/login"; // throw Exception으로 바꿀 수 있음(231005)
//        }
//    }




    //회원 목록 보기 기능(추후 삭제할 것)
//    @GetMapping(value = "/members")
//    public String list(Model model) {
//        List<Member> members = memberServiceImpl.findMembers();
//        model.addAttribute("members", members);
//        // model에 아예 모든 회원 List 만들어서 통째로 넘기기
//        return "members/memberList";
//    }
//}


