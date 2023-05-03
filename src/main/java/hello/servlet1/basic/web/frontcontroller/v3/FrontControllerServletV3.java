package hello.servlet1.basic.web.frontcontroller.v3;

import hello.servlet1.basic.web.frontcontroller.ModelView;
import hello.servlet1.basic.web.frontcontroller.MyView;
import hello.servlet1.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet1.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV1Map.put("/front-controller/v3/members/new-form", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV3 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req, resp);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

        return paramMap;
    }
}
