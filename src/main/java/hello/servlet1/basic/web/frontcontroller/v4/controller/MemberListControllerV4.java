package hello.servlet1.basic.web.frontcontroller.v4.controller;

import hello.servlet1.basic.domain.Member;
import hello.servlet1.basic.domain.MemberRepository;
import hello.servlet1.basic.web.frontcontroller.MyView;
import hello.servlet1.basic.web.frontcontroller.v2.ControllerV2;
import hello.servlet1.basic.web.frontcontroller.v4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members", members);

        return "members";
    }
}
