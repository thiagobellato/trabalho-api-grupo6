package br.com.api.g6.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice // tratamento para exceptions
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {

      List<String> erros = new ArrayList<>();

      for (FieldError error : ex.getBindingResult().getFieldErrors()) {
         erros.add(error.getDefaultMessage());
      } // Class FieldError --> pesquisar

      ErrorResponse erro = new ErrorResponse(status.value(), "Preencha todos os campos", erros);

      return super.handleExceptionInternal(ex, erro, headers, status, request);
   }

   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
      List<String> errors = new ArrayList<>();
      Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

      for (ConstraintViolation<?> violation : violations) {
         String mensagem = violation.getMessage();
         String campo = violation.getPropertyPath().toString();
         String nomeCampo = campo.substring(campo.lastIndexOf('.') + 1);
         errors.add("Campo '" + nomeCampo + "': " + mensagem);
      }

      ErrorResponse erro = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação", errors);

      return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }
}
