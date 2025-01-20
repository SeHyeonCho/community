package project.community.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import project.community.dto.SessionConst;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] whiteList = {
            "/", "/users/login", "/users/logout", "/users/create", "/css/*"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        try {
            if (!PatternMatchUtils.simpleMatch(whiteList, requestURI)) {
                HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
                    log.info("미인증 사용자");
                    response.sendRedirect("/users/login?redirectURI=" + requestURI);
                    return;
                }
            }
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {
            log.info("error",e);
        }
    }
}
