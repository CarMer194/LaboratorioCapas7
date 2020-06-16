package com.uca.capas.Laboratorio7.Domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema="public",name="ESTUDIANTE")
public class Estudiante {
	@Id
	@Column(name="idestudiante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer codigo;
	
	@Column(name="nombre")
	@Size(message="el campo no debe contener mas de 30 caracteres",max=30)
	@NotEmpty(message="Este campo no puede estar vacio")
	public String nombre;

	@Size(message="el campo no debe contener mas de 30 caracteres",max=30)
	@NotEmpty(message="Este campo no puede estar vacio")
	@Column(name="apellido")
	public String apellido;
	
	@Column(name="edad")
	@NotNull(message="el campo no debe estar vacio")
	@Min(value=18,message="No puede ser menor de 18 anios")
	public Integer edad;
	
	@Column(name="estado")
	public Boolean estado;
	
	public Estudiante() {
		super();
	}
	@OneToMany(mappedBy="estudiante",fetch=FetchType.EAGER)
	private List<Computadora> computadoras;
	
	public Estudiante(Integer codigo, String nombre, String apellido, Integer edad, Boolean estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getEstadoDelegate() {
		if(this.estado==null) return "";
		else {
			return estado==true ? "Activo":"Inactivo";
		}
	}

	public List<Computadora> getComputadoras() {
		return computadoras;
	}

	public void setComputadoras(List<Computadora> computadoras) {
		this.computadoras = computadoras;
	}
	

}
