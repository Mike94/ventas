package com.venta.servicios.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.proy.Documento;
import com.venta.proy.Producto;
import com.venta.repositorios.CategoriaRepository;
import com.venta.repositorios.ClienteRepository;
import com.venta.repositorios.DocumentoRepository;
import com.venta.repositorios.ProductoRepository;
import com.venta.servicios.ServicioVenta;

@Service
public class ServicioVentaJPA implements ServicioVenta {
	@Autowired
	private ProductoRepository repoproducto;
	@Autowired
	private CategoriaRepository repocategoria;
	@Autowired
	private ClienteRepository repocliente;
	@Autowired
	private DocumentoRepository repodocumento;

	
	public ProductoRepository getRepoproducto() {
		return repoproducto;
	}
	
	public void setRepoproducto(ProductoRepository repoproducto) {
		this.repoproducto = repoproducto;
	}

	public CategoriaRepository getRepocategoria() {
		return repocategoria;
	}
	
	public void setRepocategoria(CategoriaRepository repocategoria) {
		this.repocategoria = repocategoria;
	}
	
	public ClienteRepository getRepocliente() {
		return repocliente;
	}
	
	public void setRepocliente(ClienteRepository repocliente) {
		this.repocliente = repocliente;
	}

	public DocumentoRepository getRepodocumento() {
		return repodocumento;
	}
	
	public void setRepodocumento(DocumentoRepository repodocumento) {
		this.repodocumento = repodocumento;
	}
	
	public Producto findOneProd(Integer id) {
		return repoproducto.findOne(id);
	}
	
	public Iterable<Producto> findAllProd() {
		return repoproducto.findAll();
	}

	@Transactional
	public void saveProd(Producto producto) {
		repoproducto.save(producto);
	}
	
	@Transactional
	public void updateProd(Producto producto) {
		Producto p = repoproducto.findOne(producto.getId());
		p.setNombre(producto.getNombre());
		p.setStock(producto.getStock());
		p.setCategoria(producto.getCategoria());
		repoproducto.save(p);
	}

	@Transactional
	public void deleteProd(Producto producto) {
		repoproducto.delete(producto);
	}

	
	public Categoria findOneCat(Integer id) {
		return repocategoria.findOne(id);
	}
	
	public Iterable<Categoria> findAllCat() {
		return repocategoria.findAll();
	}

	@Transactional
	public void saveCat(Categoria categoria) {
		repocategoria.save(categoria);
	}
	
	@Transactional
	public void updateCat(Categoria categoria) {
		Categoria c = repocategoria.findOne(categoria.getId());
		c.setDenominacion(categoria.getDenominacion());
		repocategoria.save(c);
	}
	
	@Transactional
	public void deleteCat(Categoria categoria) {
		Categoria c = repocategoria.findOne(categoria.getId());
		c.setBorrado(false);
		repocategoria.save(c);
	}
	 
	public Cliente findOneCli(Integer id) {
		return repocliente.findOne(id);
	}
	
	public Iterable<Cliente> findAllCli() {
		return repocliente.findAll();
	}

	@Transactional
	public void saveCli(Cliente cliente) {
		repocliente.save(cliente);
	}
	
	@Transactional
	public void updateCli(Cliente cliente) {
		Cliente c = repocliente.findOne(cliente.getId());
		c.setDni(cliente.getDni());
		c.setNombres(cliente.getNombres());
		c.setApellidos(cliente.getApellidos());
		c.setDireccion(cliente.getDireccion());
		repocliente.save(c);
	}
	
	@Transactional
	public void deleteCli(Cliente cliente) {
		repocliente.delete(cliente);
	}
	
	public Documento findOneDoc(Integer id) {
		return repodocumento.findOne(id);
	}
	
	public Iterable<Documento> findAllDoc() {
		return repodocumento.findAll();
	}

	@Transactional
	public void saveDoc(Documento documento) {
		Documento d = repodocumento.findOne(documento.getId());
		d.setNombre(documento.getNombre());
		repodocumento.save(d);
	}
	
	@Transactional
	public void updateDoc(Documento documento) {
		repodocumento.update(documento);
	}
	
	@Transactional
	public void deleteDoc(Documento documento) {
		repodocumento.delete(documento);
	}


}
