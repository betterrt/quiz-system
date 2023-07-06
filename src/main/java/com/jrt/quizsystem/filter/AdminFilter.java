package com.jrt.quizsystem.filter;


import com.jrt.quizsystem.domain.User;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter extends OncePerRequestFilter {
   @Override
   protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {
      HttpSession session = request.getSession(false);
      if (session != null && session.getAttribute("user") != null) {
         User user = (User) session.getAttribute("user");
         if(user.getIsAdmin()) {
            filterChain.doFilter(request, response);
         } else {
            response.sendRedirect("/home");
         }
      } else {
         // redirect back to the login page if user is not logged in
         response.sendRedirect("/login");
      }
   }
}
