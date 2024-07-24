package com.loco.demo.System.ExceptionHandlerManager;

import com.loco.demo.DTO.JSON.ExceptionResponseHandler;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class ExceptionHandleManagers {

    @ExceptionHandler({ UsernameNotFoundException.class, BadCredentialsException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ExceptionResponseHandler handleUnauthorizedException(Exception e) {
        return new ExceptionResponseHandler(
                StatusResponseAPI.UNAUTHORIZED, "000", "username or password is incorrect.", e.getMessage());
    }

    @ExceptionHandler(InstantiationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ExceptionResponseHandler handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        return new ExceptionResponseHandler(
                StatusResponseAPI.UNAUTHORIZED, "000", "Login credentials are missing.", ex.getMessage());
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ExceptionResponseHandler handleAccountStatusException(AccountStatusException ex) {
        return new ExceptionResponseHandler(
                StatusResponseAPI.UNAUTHORIZED, "000", "User account is abnormal.", ex.getMessage());
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ExceptionResponseHandler handleInvalidBearerTokenException(InvalidBearerTokenException ex) {
        return new ExceptionResponseHandler(StatusResponseAPI.UNAUTHORIZED, "000",
                "The access token provided is expired, revoked, malformed, or invalid for other reasons.",
                ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ExceptionResponseHandler handleAccessDeniedException(AccessDeniedException ex) {
        return new ExceptionResponseHandler(StatusResponseAPI.FORBIDDEN, "000", "No permission.", ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionResponseHandler handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new ExceptionResponseHandler(StatusResponseAPI.NOT_FOUND, "000", "This API endpoint is not found.",
                ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionResponseHandler handleInternalServerError(Exception ex) {
        return new ExceptionResponseHandler(StatusResponseAPI.INTERNAL_SERVER_ERROR, "000",
                "A server internal error occurs.", ex.getMessage());
    }
}