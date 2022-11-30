package com.example.controller.Interceptor;

import com.example.util.GetSessionUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Configuration
public class ProjectInterceptor implements HandlerInterceptor {
    /**
     * 访问控制器方法前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("asda");
//        String studentSession = GetSessionUtil.getSession(request, "studentSession");
//        System.out.println("studentSession:" + studentSession);
//        if(studentSession == null){

//            return false;
//        }else{
//            System.out.println("返回...");
//            request.getRequestDispatcher("/login.html").forward(request, response);
//        System.out.println(new Date() + "--preHandle:" + request.getRequestURL());

        if(GetSessionUtil.getSession(request, "studentSession") == null){
            request.getRequestDispatcher("/pages/login.html").forward(request, response);
            return false;
        }else return true;
    }


//    /**
//     * 访问控制器方法后执行
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        System.out.println(new Date() + "--postHandle:" + request.getRequestURL());
//    }
//
//    /**
//     * postHandle方法执行完成后执行，一般用于释放资源
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        System.out.println(new Date() + "--afterCompletion:" + request.getRequestURL());
//    }
}
