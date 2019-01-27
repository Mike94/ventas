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

import com.venta.proy.Documento;
import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	ServicioVenta servicio;
	
	@RequestMapping("/index")
	public String lista(Model modelo) {
		//Envia a la vista es decir a la plantilla todas las Documentos
		modelo.addAttribute("documentos", servicio.findAllDoc());
		
		//cat-index.html en la carpeta documento
		return "documento/doc-index";
	}
	
	@RequestMapping("/new")
	public String newDocumento(Model modelo) {
		modelo.addAttribute(new Documento());
		return "documento/doc-new";
	}
	
	// Ya tenemos el objeto  documento lleno con los datos del formulario
	@RequestMapping(value="/insertarDocumento",method=RequestMethod.POST)
	public String insertarDocumento( @Valid @ModelAttribute Documento documento, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			return "documento/doc-new";
		}else {
			servicio.saveDoc(documento);
			return "redirect:/documento/index";
		}			
	}
	
	@RequestMapping("/editDocumento")
	public String editDocumento(@RequestParam("clave") Integer id, Model modelo) {
		modelo.addAttribute("documento", servicio.findOneDoc(id));
		return "documento/doc-edit";
	}
	
	@RequestMapping(value="/updateDocumento", method=RequestMethod.POST)
	public String updateDocumento(@Valid @ModelAttribute Documento documento, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			return "documento/editDocumento";
		} else {
//			La del profesor Dayan
			Documento d = servicio.findOneDoc(documento.getId());
			d.setNombre(documento.getNombre());
			servicio.saveDoc(d);
			
//			servicio.updateDoc(documento);
			return "redirect:/documento/index";
		}
	}
	
	@RequestMapping("/borrarDocumento")
	public String borrarDocumento(@RequestParam("clave") Integer id, Model modelo) {
		servicio.deleteDoc(new Documento(id));
		return "redirect:/documento/index";		
	}
}


