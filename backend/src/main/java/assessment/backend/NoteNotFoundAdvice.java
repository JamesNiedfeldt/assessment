/**
 * A class for the Spring framework to create a useful HTTP response when
 * a NoteNotFoundException is thrown.
 * 
 * @author James Niedfeldt
 */

package assessment.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoteNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noteNotFoundHandler(NoteNotFoundException e) {
        return e.getMessage();
    }
}