package com.venta.proy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "documento")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String nombre;

//	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Producto> productos = new ArrayList<Producto>();

	public Documento(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Documento(int id) {
		super();
		this.id = id;
	}

	public Documento() {
		super();
	}

//	public void addProducto(Producto p) {
//		productos.add(p);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<Producto> getProductos() {
//		return productos;
//	}
//
//	public void setProductos(List<Producto> productos) {
//		this.productos = productos;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
