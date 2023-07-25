package br.com.vr.miniautorizador.api.errors;


import br.com.vr.miniautorizador.domain.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler  {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ModeloErroHandler> handle(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ModeloErroHandler.builder()
                        .data(LocalDateTime.now())
                        .dadosErro(List.of(
                                DadosMensagemErro.builder()
                                        .campo("Not Found")
                                        .erro(e.getMessage())
                                        .build()
                        ))
                        .build());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ModeloErroHandler> handle(BusinessException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ModeloErroHandler.builder()
                        .data(LocalDateTime.now())
                        .dadosErro(List.of(
                                DadosMensagemErro.builder()
                                        .campo("Erro")
                                        .erro(e.getMessage())
                                        .build()
                        ))
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ModeloErroHandler> handle(ConstraintViolationException e) {
        List<DadosMensagemErro> errors = e.getConstraintViolations().stream()
                .map(c -> DadosMensagemErro.builder()
                                .campo(c.getPropertyPath().toString())
                                .erro(c.getMessage())
                                .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ModeloErroHandler.builder()
                                     .data(LocalDateTime.now())
                                     .dadosErro(errors)
                                     .build());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

}
