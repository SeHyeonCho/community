package project.community.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = UUID.randomUUID().toString();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        try {
            log.info("REQUEST [{}][{}][{}]", uuid, request.getDispatcherType(), request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.info("filter exception",e);
        }
        finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, request.getDispatcherType(), request.getRequestURI());

        }

    }
}
