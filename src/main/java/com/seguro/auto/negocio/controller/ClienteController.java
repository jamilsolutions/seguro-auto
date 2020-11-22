package com.seguro.auto.negocio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.seguro.auto.negocio.Cliente;
import com.seguro.auto.negocio.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping("/salvar")
	public Cliente criar(@Validated @RequestBody Cliente cliente) throws Exception {
		cliente = this.clienteService.salvar(cliente);
		return cliente;
	}

	@GetMapping("/ler/{cpf}")
	public Cliente ler(@PathVariable String cpf) {
		Optional<Cliente> cliente = this.clienteService.consultar(cpf);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}

	@DeleteMapping("/excluir/{cpf}")
	public ResponseEntity<String> remover(@PathVariable String cpf) throws Exception {
		String message = this.clienteService.remover(cpf);
		if ( message == null) {
		return ResponseEntity.status(HttpStatus.OK)
		        .body("{ \"message\": \"Cliente "+ cpf +" excluído com sucesso\" }");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"message\": \"" + message + "\" }");
		}
	}

	@GetMapping("/listar")
	public List<Cliente> listar() {
		List<Cliente> clientes = this.clienteService.listar();
		return clientes; 
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
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleValidationExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<String, String>();
		FieldError error = new FieldError("Exception", "Exceção", ex.getMessage());
		String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
	    return errors;
	}

}
