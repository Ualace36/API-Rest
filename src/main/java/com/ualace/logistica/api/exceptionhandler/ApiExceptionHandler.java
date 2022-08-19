package com.ualace.logistica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ualace.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.logistica.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

//essa notação diz que a class faz o controle de exceção global
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
   
	
	private MessageSource messageSource;
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    	List<Problema.Campo> campos = new ArrayList<>();
    	
    	for(ObjectError error : ex.getBindingResult().getAllErrors()) {
    		String nome = ((FieldError) error).getField();
    		String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
    		
    		campos.add(new Problema.Campo(nome, mensagem));
    		
    		
    	}
    		
    		
    		
    	
    	var problema = new Problema();
    	problema.setStatus(status.value());
    	problema.setDataHora(OffsetDateTime.now());
    	problema.setTitulo("Error validation: um ou mais campos estão invalidados. Faça o preenchimento correto e tente novamente.");
		problema.setCampos(campos);
    	return handleExceptionInternal(ex, problema, headers, status, request);
	
	}
	
	//esse é o método resposável para tratar a "exception" NegocioException
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest requeste){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		var problema = new Problema();
    	problema.setStatus(status.value());
    	problema.setDataHora(OffsetDateTime.now());
    	problema.setTitulo(ex.getMessage());
		
    	
		return handleExceptionInternal (ex, problema,new HttpHeaders(), status, requeste);
	
	
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handNegocio(NegocioException ex, WebRequest requeste){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		var problema = new Problema();
    	problema.setStatus(status.value());
    	problema.setDataHora(OffsetDateTime.now());
    	problema.setTitulo(ex.getMessage());
		
    	
		return handleExceptionInternal (ex, problema,new HttpHeaders(), status, requeste);
	
	}
	
	
	
}
