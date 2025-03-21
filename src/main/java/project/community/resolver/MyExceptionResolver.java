package project.community.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("resolver solve IllegalArgumentException");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
                return new ModelAndView();
            }
        } catch (Exception e) {
            log.info("exception");
        }
        return null;
    }
}
