/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.clientecategoriasjson;

import com.fpmislata.domain.Categoria;
import com.fpmislata.domain.Producto;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;

/**
 *
 * @author Maria
 */
public class ProductoClienteTest {
    public static void main(String[] args) {
        Client client = Client.create();
        
        //Listar productos
        System.out.println("Listado productos");
        WebResource web = client.resource("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos");
        List<Producto> productos = web.get(new GenericType<List<Producto>>(){});
        for(Producto p : productos){
            System.out.println(p.getId()+" - "+p.getNombre()+" - "+p.getPrecio()+" - "+p.getCategoria());
        }
        
        System.out.println("----------------------");
        
        //Mostrar producto concreto
        System.out.println("Producto concreto");
        web = client.resource("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos/findById/2");
        Producto producto = web.get(Producto.class);
        System.out.println(producto.getNombre()+" - "+producto.getPrecio()+" - "+producto.getCategoria());
        
        System.out.println("----------------------");
        
        //Añadir producto
        System.out.println("Añadir producto");
        web = client.resource("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos/add");
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Producto nuevo");
        nuevoProducto.setPrecio(20F);
        
        Categoria categoria = new Categoria();
        categoria.setNombre("Nueva categoria");
        
        nuevoProducto.setCategoria(categoria);
        
        ClientResponse response = web.post(ClientResponse.class, nuevoProducto);
        
        System.out.println("Código de respuesta: "+response.getStatus());
        
        if(response.getStatus() == 200){
            Producto prod = response.getEntity(Producto.class);
            System.out.println("Nuevo producto: "+prod.getNombre()+" - "+prod.getPrecio()+" - "+prod.getCategoria());
        }
        
        System.out.println("----------------------");
    }
}
