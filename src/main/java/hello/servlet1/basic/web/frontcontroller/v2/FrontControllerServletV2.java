package hello.servlet1.basic.web.frontcontroller.v2;

import hello.servlet1.basic.web.frontcontroller.MyView;
import hello.servlet1.basic.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet1.basic.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV2 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        MyView view = controller.process(req, resp);
        view.render(req, resp);

    }
}
