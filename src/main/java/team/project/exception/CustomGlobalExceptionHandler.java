package team.project.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String,Object> bodyErrors = new LinkedHashMap<>();
        bodyErrors.put("timestamp", LocalDateTime.now());
        bodyErrors.put("status", HttpStatus.BAD_REQUEST);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        bodyErrors.put("errors", errors);
        return new ResponseEntity<>(bodyErrors, headers, status);
    }

    @ExceptionHandler(EntityNotFoundCustomException.class)
    protected ResponseEntity<Object> handleEntityNotFoundCustomException(
            EntityNotFoundCustomException ex) {
        return generateErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ExceptionHandler(RegistrationCustomException.class)
    protected ResponseEntity<Object> handleRegistrationCustomException(
            RegistrationCustomException ex) {
        return generateErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage());
    }

    @ExceptionHandler(EntityExistsCustomException.class)
    protected ResponseEntity<Object> handleEntityExistsCustomException(
            EntityExistsCustomException ex) {
        return generateErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage());
    }

    private ResponseEntity<Object> generateErrorResponse(HttpStatus status, String errorText) {
        Map<String,Object> bodyErrors = new LinkedHashMap<>();
        bodyErrors.put("timestamp", LocalDateTime.now());
        bodyErrors.put("status", status);
        bodyErrors.put("error", errorText);
        return new ResponseEntity<>(bodyErrors, status);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError errorField) {
            String field = errorField.getField();
            String message = errorField.getDefaultMessage();
            return field + " " + message;
        }
        return error.getDefaultMessage();
    }
}
