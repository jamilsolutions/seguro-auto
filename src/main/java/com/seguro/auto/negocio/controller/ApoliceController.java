package com.seguro.auto.negocio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seguro.auto.negocio.Apolice;
import com.seguro.auto.negocio.service.ApoliceService;

@RestController
@RequestMapping("/apolice")
public class ApoliceController {

	private ApoliceService apoliceService;

	public ApoliceController(ApoliceService apoliceService) {
		this.apoliceService = apoliceService;
	}

	@PostMapping("/salvar")
	public Apolice salvar(@Validated @RequestBody Apolice apolice) {
		if ( apolice != null && (apolice.getNumeroApolice() == null || apolice.getNumeroApolice() == 0) ) {
			long numeroApolice = Math.round(Math.random()*1000000000);
			apolice.setNumeroApolice(numeroApolice);
		}
		try {
			apolice = this.apoliceService.salvar(apolice);
		} catch (Exception e) {
			apolice.setCpf(apolice.getCpf() + ":Inválido");
			return apolice;
		}
		return apolice;
	}
	
	@GetMapping("/ler/{numeroApolice}")
	public Apolice ler(@PathVariable Long numeroApolice) {
		Optional<Apolice> apolice = this.apoliceService.consultar(numeroApolice);
		if (apolice.isPresent()) {
			return apolice.get();
		}
		return null;
	}

	@DeleteMapping("/excluir/{numeroApolice}")
	public ResponseEntity<String> excluir(@PathVariable Long numeroApolice) {
		this.apoliceService.remover(numeroApolice);
		
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"message\": \"Apolice "+ numeroApolice +" excluída com sucesso\" }");
	}

	@GetMapping("/listar")
	public List<Apolice> listar() {
		List<Apolice> apolices = this.apoliceService.listar();
		return apolices;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> handleValidationExceptions(HttpMessageNotReadableException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		FieldError error = new FieldError("HttpMessageNotReadableException", "Vigência", ex.getMessage());
		String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
	    return errors;
	}

}
