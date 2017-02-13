/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name="clientes")
@NamedQueries({@NamedQuery(name="cliente.findAll", query ="SELECT c FROM Cliente c ORDER BY c.id")})
@XmlRootElement
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose 
    private int id;
    
    @Column(nullable=false, length=50)
    @Expose 
    private String nombre;
    
    @Column(nullable=false, length=100)
    @Expose 
    private String apellidos;
    
    @Column(nullable=false, length=9)
    @Expose 
    private String nif;
    
    @Column(nullable=true, length=100)
    @Expose 
    private String direccion;
    
    @Column(nullable=true, length=100)
    @Expose 
    private String poblacion;
    
    @Column(nullable=true, length=100)
    @Expose 
    private String provincia;
    
    @Column(nullable=true, length=5)
    @Expose 
    private String codigo_postal;
    
    @Column(nullable=false, length=9)
    @Expose 
    private String telefono;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String nif, String direccion, String poblacion, String provincia, String codigo_postal, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigo_postal = codigo_postal;
        this.telefono = telefono;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.apellidos);
        hash = 59 * hash + Objects.hashCode(this.nif);
        hash = 59 * hash + Objects.hashCode(this.direccion);
        hash = 59 * hash + Objects.hashCode(this.poblacion);
        hash = 59 * hash + Objects.hashCode(this.provincia);
        hash = 59 * hash + Objects.hashCode(this.codigo_postal);
        hash = 59 * hash + Objects.hashCode(this.telefono);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nif=" + nif + ", direccion=" + direccion + ", poblacion=" + poblacion + ", provincia=" + provincia + ", codigo_postal=" + codigo_postal + ", telefono=" + telefono + '}';
    }
    
}
