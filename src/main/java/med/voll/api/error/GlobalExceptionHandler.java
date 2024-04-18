package med.voll.api.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class, EntityNotFoundException.class})
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(DataErrorsValidator::new).toList());
    }

    private record DataErrorsValidator(String field, String message) {

        public DataErrorsValidator(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
