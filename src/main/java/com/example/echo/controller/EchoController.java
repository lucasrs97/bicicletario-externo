package com.example.echo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/echo")
@Api(value="Spring Boot REST API Echo")
@CrossOrigin(origins="*")
public class EchoController {

	@GetMapping("/{palavra}")
	@ApiOperation(value="Retorna repetição da palavra informada")
	public String getEcho (@PathVariable (value ="palavra") String palavra){
		return palavra +" "+ palavra +" "+ palavra;					
	}
	
	@GetMapping("/")
	@ApiOperation(value="Retorna informação sobre a API")
	public String getRoot () {
		return "Isto é um eco, adicione uma palavra no caminho.";					
	} 
	
}