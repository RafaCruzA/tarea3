package com.uca.capas.tarea3.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public String ingresar() {
		return "ingresar";
	}
	
	@RequestMapping("/alumno")
	public ModelAndView ingresar(@RequestParam String nombre, @RequestParam String apellidos, @RequestParam String fechaNacimiento, @RequestParam String lugarNacimiento, @RequestParam String instituto, @RequestParam String telFijo, @RequestParam String celular) throws ParseException {
		ModelAndView mav = new ModelAndView();
		List<String> lista = new ArrayList<>();
		
		Date minBD = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2003");
		Date fecNac = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);

	
		if(nombre.length()<1) {
		lista.add("El campo Nombre debe llevar al menos un caracter");
		}
		
		if(nombre.length()>25) {
			lista.add("El campo Nombre no puede llevar mas de 25 caracteres");
			}

		if(apellidos.length()<1) {
			lista.add("El campo Apellido debe llevar al menos un caracter");
		}
		
		if(apellidos.length()>25) {
			lista.add("El campo Apellido no puede llevar mas de 25 caracteres");
		}
		
		if(fecNac.before(minBD)==true){
			lista.add("La fecha de nacimiento no puede ser antes del 01/01/2003");
		}
		
		if(lugarNacimiento.length()<1) {
			lista.add("El campo Lugar de nacimiento debe llevar al menos un caracter");
		}
		
		
		if(lugarNacimiento.length()>25) {
			lista.add("El campo Lugar de nacimiento no puede llevar mas de 25 caracteres");
		}
		
		if(instituto.length()<1) {
			lista.add("El campo Instituto o Colegio de procedencia debe contener al menos un caracter");
		}
		
		
		if(instituto.length()>100) {
			lista.add("El campo Instituto o Colegio de procedencia no puede llevar mas de 100 caracteres");
		}
		
		
		if(isNumeric(telFijo)== false) {
			lista.add("El campo Telefono fijo debe contener solamente numeros");
		}else {
			if(telFijo.length() != 8) {
				lista.add("El numero de telefono debe contener exactamente 8 numeros");
			}
		}
		
		
		if(isNumeric(celular)== false) {
			lista.add("El campo Telefono movil debe contener solamente numeros");
		}else {
			if(celular.length() != 8) {
				lista.add("El Numero de celular debe contener exactamente 8 numeros");
				}
		}
		
		mav.addObject("errores",lista);
		
		if(lista.size() == 0) {
			mav.setViewName("/alumno");
		}else {
			
			mav.setViewName("/errores");
		}
		
		return mav;
	
		
	}
	
	boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
