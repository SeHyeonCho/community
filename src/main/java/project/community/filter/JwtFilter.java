package project.community.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import project.community.service.JwtService;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getHeader(request).ifPresent((token)-> {

            Long userid = jwtService.getUserByToken(token);
        });
    }

    private static Optional<String> getHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            return Optional.empty();
        }
        return checkJwt(header);
    }

    private static Optional<String> checkJwt(String header) {
        String[] str = header.split(" ");
        if (str.length == 2) {
            return Optional.of(str[1]);
        }
        return Optional.empty();
        //oauth 추가 공부
    }
}
