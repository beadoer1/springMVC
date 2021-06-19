package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

    boolean suports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler);
}
