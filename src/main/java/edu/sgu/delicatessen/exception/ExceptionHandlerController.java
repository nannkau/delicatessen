package edu.sgu.delicatessen.exception;


import javax.validation.ConstraintViolationException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import edu.sgu.delicatessen.dto.response.BaseResponse;
import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<String> handleConstraintViolationException(ConstraintViolationException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
	@ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<String> handleResourceNotFoundException(NotFoundException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<String> handleAllExceptions(Exception e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse<String> handleAccessDeniedException(AccessDeniedException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.FORBIDDEN.value());
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleAuthenticationException(AuthenticationException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> badCredentialsException(BadCredentialsException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<String> expiredJwtException(ExpiredJwtException e,WebRequest request) {
        return  new BaseResponse<>(e.getMessage(),HttpStatus.UNAUTHORIZED.value());
    }
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<String> handleCustomizedException(ApiException e) {
        return  new BaseResponse<>(e.getMessage(),e.getCode());
    }
}
