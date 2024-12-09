package com.byma.emisor.exception_handler;

import com.byma.emisor.application.exception.*;
import com.byma.emisor.application.exception.acdi.AcdiNoEncontradoException;
import com.byma.emisor.application.exception.especie.EspecieConIdExistenteException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EmisorNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleEmisorNoEncontradoException(EmisorNoEncontradoException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BilleteraNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleBilleteraNoEncontradoException(BilleteraNoEncontradoException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmisorDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageResponse handleEmisorDuplicadoException(EmisorDuplicadoException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(GerenteNoEncontradoException.class)
    public ErrorMessageResponse handleGerenteNoEncontrado(GerenteNoEncontradoException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AcdiNoEncontradoException.class)
    public ResponseEntity<?> manejarAcdiNoEcontradoException(Exception exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EspecieNoEncontradaException.class)
    public ErrorMessageResponse handleEspecieNoEncontradaException(Exception exception, HttpServletRequest request) throws ObjetoEnviadoNuloException {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EspecieConIdExistenteException.class)
    public ErrorMessageResponse handleEspecieConIdExistenteException(Exception exception, HttpServletRequest request) throws ObjetoEnviadoNuloException {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjetoNuloException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageResponse handleObjetoNuloException(ObjetoNuloException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdNuloException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageResponse handleIdNuloException(ObjetoNuloException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SuscripcionNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleSuscripcionNoEncontradaException(ObjetoNuloException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorDetails = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String completeMessage = "Parametros invalidos en la solicitud : " + errorDetails;

        // Construir el objeto ErrorMessageResponse
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .exception(ex.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(completeMessage)
                .path(request.getDescription(false).replace("uri=", ""))
                .method(request.getHeader("X-HTTP-Method-Override"))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageResponse);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageResponse handleException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ErrorMessageResponse createErrorMessageResponse(Exception exception, HttpServletRequest request, HttpStatus status) {
        return ErrorMessageResponse.builder()
                .exception(exception.getClass().getName())
                .message(exception.getMessage())
                .status(status.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();
    }

}