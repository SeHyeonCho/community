package project.community.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    private static final String[] whiteList = {"/css/*"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = UUID.randomUUID().toString();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        try {
            if (!PatternMatchUtils.simpleMatch(whiteList, requestURI)) {
                log.info("REQUEST [{}][{}][{}]", uuid, request.getDispatcherType(), request.getRequestURI());
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.info("filter exception",e);
        }
        finally {
            if (!PatternMatchUtils.simpleMatch(whiteList, requestURI)) {
                log.info("RESPONSE [{}][{}][{}]", uuid, request.getDispatcherType(), request.getRequestURI());
            }

        }

    }
}
