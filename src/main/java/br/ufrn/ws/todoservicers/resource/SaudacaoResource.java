package br.ufrn.ws.todoservicers.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaudacaoResource {
	
	@RequestMapping("/saudacao")
	public String saudacao() {
		return "Olá, Mundo!!!";
	}

}
