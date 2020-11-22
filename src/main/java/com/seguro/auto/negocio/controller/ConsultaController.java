package com.seguro.auto.negocio.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seguro.auto.negocio.Apolice;
import com.seguro.auto.negocio.service.ApoliceService;
import com.seguro.auto.negocio.util.DateUtil;

@RestController
@RequestMapping("/consulta/apolice")
public class ConsultaController {
	
   private static final Logger log = LoggerFactory.getLogger(ConsultaController.class);
		
	private ApoliceService apoliceService;
	
	public ConsultaController(ApoliceService apoliceService) {
		this.apoliceService = apoliceService;
	}
	
	@GetMapping("/placa/{placaVeiculo}")
    public List<Apolice> consultar(@PathVariable String placaVeiculo) {
		List<Apolice> apolice = this.apoliceService.findByPlacaVeiculo(placaVeiculo);
		return apolice;
	}
	
	@GetMapping("/vencida/{numeroApolice}")
	public ResponseEntity<String> venceu(@PathVariable Long numeroApolice) {
		Optional<Apolice> apolice = this.apoliceService.consultar(numeroApolice);
		boolean vencida = false;
		if ( apolice.isPresent() ) {
			if ( apolice.get().getFimVigencia().before(Calendar.getInstance().getTime())) {
				vencida = true;
			}			
		}
		return ResponseEntity.status(HttpStatus.OK)
		        .body("{ \"vencida\" : \"" + vencida + "\" }");		
	}
	
	@GetMapping("/dias/{numeroApolice}")
	public ResponseEntity<String> dias(@PathVariable Long numeroApolice) {
		Long dias = 0L;
		Optional<Apolice> apolice = this.apoliceService.consultar(numeroApolice);
		if ( apolice.isPresent() ) {
			try {
				dias = DateUtil.calcular(apolice.get().getFimVigencia(), Calendar.getInstance().getTime());
			} catch (ParseException e) {
				log.error("Falha ao calcular a data fim de vigência em dias.");
			}	
		}
		//Informar quantos dias para vencer, ou há quantos dias venceu
		if ( dias > 0 ) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"placa\":\"" + apolice.get().getPlacaVeiculo() + "\", \"valor\":\"" + apolice.get().getValorApolice() + "\",  \" \"dias para vencer\" : \"" + dias + "\" }");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"placa\":\"" + apolice.get().getPlacaVeiculo() + "\", \"valor\":\"" + apolice.get().getValorApolice() + "\", \"dias já vencida\" : \"" + -dias + "\" }");
		}
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
}
