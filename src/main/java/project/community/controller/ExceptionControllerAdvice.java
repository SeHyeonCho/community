package project.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.community.domain.User;
import project.community.dto.ErrorData;
import project.community.interceptor.Login;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity<ErrorData> IllegalArgumentEx(IllegalArgumentException ex) {
        log.error("Controller Advice - IllegalArgumentEx");
        ErrorData errorData = new ErrorData("IllegalArgument", ex.getMessage());
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }
}
