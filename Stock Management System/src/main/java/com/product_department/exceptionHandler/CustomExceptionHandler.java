package com.product_department.exceptionHandler;

import com.product_department.exceptions.CouldNotAddException;
import com.product_department.exceptions.NoContentAvailableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    /* MethodArgumentNotValidException occurs when
     * --> using @Valid / @Validated
    * --> request body or param or attribute not valid
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> invalidInput(MethodArgumentNotValidException exception)
    {
        Map<String ,String > errorMap = new HashMap<>();

        errorMap.put("status code" , exception.getStatusCode().toString() );
        errorMap.put("cause", exception.getParameter().toString());
        exception.getFieldErrors().forEach(e-> errorMap.put(e.getField() , e.getDefaultMessage()));

        return errorMap;
    }

    /* HttpMessageNotReadableException occurs when
    * --> bad request body (json/xml)
    * --> invalid data
    * --> mismatch
    * --> missing request body
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String , String > emptyRequest(HttpMessageNotReadableException exception)
    {
        Map<String ,String > errorMap = new HashMap<>();
        errorMap.put("error messaage", exception.getMessage());
        return  errorMap;
    }

    /* NoContentAvailableException occurs when
    * --> searched content is not there in database
    * --> content is null (insteaded of nullpointerException)
    */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoContentAvailableException.class)
    public Map<String , String > noContent(NoContentAvailableException exception)
    {
        Map<String ,String > errorMap = new HashMap<>();
        errorMap.put("Status Code " , HttpStatus.NOT_FOUND.toString());
        errorMap.put(exception.getField(), exception.getLocalizedMessage());
        return errorMap;
    }

    /* CouldNotAddException occurs when
    * --> unsatisfied input
    * --> internal issues while persisting data into database
    * --> some service logic errors
    */
    @ExceptionHandler(CouldNotAddException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> addingProblem(CouldNotAddException e)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Products" , e.getMessage());
        return errorMap;
    }

    /* MissingServletRequestParameterException occurs when
    * --> missing parameters (KEY = VALUE) pair
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> missingServletRequestParameterException(MissingServletRequestParameterException exception)
    {
        Map<String ,String> errorMap =  new HashMap<>();
        errorMap.put("Status code" , exception.getStatusCode().toString());
        errorMap.put("Parameter ",exception.getParameterName());
        errorMap.put("error message" ,exception.getMessage());

        return errorMap;
    }

    /* DataIntegrityViolationException occurs when
    *--> add a Duplicate in entity
    * --> adding null
    * --> deleting parent which is foreign key in child entity
    * --> unique constrints
    */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String , String> dataViolationException(DataIntegrityViolationException exception)
    {
        Map<String , String> errorMap = new HashMap<>();

        errorMap.put("status code" , HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorMap.put("error message" , exception.getMessage());
        errorMap.put("cause" , " duplicate / null / trying to delete parent entity ");

        return errorMap;
    }
}
