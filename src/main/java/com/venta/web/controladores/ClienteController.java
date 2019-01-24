package com.venta.web.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ServicioVenta servicio;
	
	@RequestMapping("/index")
	public String lista(Model modelo) {
		//Envia a la vista es decir a la plantilla todos los Clientes
		modelo.addAttribute("clientes", servicio.findAllCli());
		
		//cli-index.html en la carpeta cliente
		return "cliente/cli-index";
	}
	
	@RequestMapping("/new")
	public String fNuevoCliente(Model modelo) {
		modelo.addAttribute(new Cliente());
		return "cliente/cli-new";
	}
	
	// Ya tenemos el objeto  cliente lleno con los datos del formulario
	@RequestMapping(value="/insertarCliente",method=RequestMethod.POST)
	public String insertarCliente( @Valid @ModelAttribute Cliente cliente, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {			
			return "cliente/cli-new";
		}else {
			servicio.saveCli(cliente);
			modelo.addAttribute("clientes", servicio.findAllCli());
			return "cliente/cli-index";
		}	
	}
	
	@RequestMapping("/editCliente")
	public String editCliente(@RequestParam("clave") Integer id, Model modelo) {
		modelo.addAttribute("cliente", servicio.findOneCli(id));
		return "cliente/cli-edit";
	}
	
	@RequestMapping(value="/updateCliente", method=RequestMethod.POST)
	public String updateCliente(@Valid @ModelAttribute Cliente cliente, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			return "cliente/edit";
		} else {
			servicio.updateCli(cliente);
			return "redirect:/cliente/index";
		}
	}
	
	@RequestMapping("/borrarCliente")
	public String borrarCliente(@RequestParam("clave") Integer id, Model modelo) {
		servicio.deleteCli(new Cliente(id));
		modelo.addAttribute("clientes", servicio.findAllCat());
		return "cliente/cli-index";
	}	
	
}


