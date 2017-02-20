/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.clientecategoriasjson;

import com.fpmislata.domain.Categoria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class ClienteTest {

    public static void main(String[] args) throws IOException {
        
        // ***********************
        // *** LISTAR CATEGORIA
        // ***********************     
        System.out.println("Lista de categorias del sistema");
        System.out.println("---------------------------");
        List<Categoria> lista = getListCategoria("http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriaService/Categorias");
        for (Categoria categoria : lista) {
            System.out.println(categoria.toString());
        }
        System.out.println("----------------------------\n");

        // **********************************************
        // *** RECUPERAMOS UNA CATEGORIA EN CONCRETO
        // **********************************************
        System.out.println("Recuperando una categoria en concrero del sistema");
        Categoria p = getCategoria("http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriaService/Categorias/findById/1");
        System.out.println("La categoria recuperada es: " + p.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** AÑADIMOS UNA CATEGORIA AL SISTEMA
        // **********************************************        
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Nuevisima categoría");

        System.out.println("Insertando una nueva categoria en el sistema");
        Categoria p2 = addCategoria("http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriaService/Categorias/add", nuevaCategoria);
        System.out.println("La categoria insertada es: " + p2.toString());
        System.out.println("----------------------------\n");
        
        // **********************************************
        // *** ACTUALIZAMOS UNA CATEGORIA EN EL SISTEMA
        // **********************************************        
        Categoria categoriaExistente = new Categoria();
        categoriaExistente.setNombre("categoria existente2");
        categoriaExistente.setId(8);
        
        System.out.println("Modificando una categoria en el sistema");
        Categoria p3 = updateCategoria("http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriaService/Categorias/update/6", categoriaExistente);
        System.out.println("La categoria modificada es ahora: " + p3.toString());
        System.out.println("----------------------------\n");        

        
        // **********************************************
        // *** BORRAMOS UNA CATEGORIA AL SISTEMA
        // **********************************************         
        System.out.println("Recuperando una categoria en concrero del sistema");
        deleteCategoria("http://localhost:8080/ProyectoFinal20162017-web/webservice/CategoriaService/Categorias/delete/4");
        System.out.println("La categoria recuperada es: " + p.toString());
        System.out.println("----------------------------\n");   
    }
    
    // Obtenemos la lista de categorias
    private static List<Categoria> getListCategoria(String url) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String lista = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Creamos el tipo generico que nos va a permitir devolver la lista a partir del JSON que esta en el String
        Type type = new TypeToken<List<Categoria>>() {
        }.getType();
        // Parseamos el String lista a un objeto con el gson, devolviendo así un objeto List<Categoria>
        return gson.fromJson(lista, type);
    }

    // Obtenemos una categoria en concreto
    private static Categoria getCategoria(String url) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String categoriaString = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el String categoria a un objeto con el gson, devolviendo así un objeto Categoria
        return gson.fromJson(categoriaString, Categoria.class);
    }

    // Añadimos una categoria al sistema
    private static Categoria addCategoria(String url, Categoria categoria) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPost httpPost = new HttpPost(url);
        // Añade las cabeceras al metodo
        httpPost.addHeader("accept", "application/json; charset=UTF-8");
        httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(categoria);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPost.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPost);

        // Comprobamos si ha fallado
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String categoriaResult = readObject(response);
        return gson.fromJson(categoriaResult, Categoria.class);
    }
    
    // Borramos una categoria al sistema
    private static void deleteCategoria(String url) {
        try {
            // Crea el cliente para realizar la conexion
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // Crea el método con el que va a realizar la operacion
            HttpDelete delete = new HttpDelete(url);
            // Añade las cabeceras al metodo
            delete.addHeader("accept", "application/json; charset=UTF-8");
            delete.addHeader("Content-type", "application/json; charset=UTF-8");
            // Invocamos el servicio rest y obtenemos la respuesta
            HttpResponse response = httpClient.execute(delete);
            String status = response.getStatusLine().toString();

            // Comprobamos si ha fallado
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
            }else{
                System.out.println("Se ha eliminado la categoria correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    private static Categoria updateCategoria(String url, Categoria categoria) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPut httpPut = new HttpPut(url);
        // Añade las cabeceras al metodo
        httpPut.addHeader("accept", "application/json; charset=UTF-8");
        httpPut.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(categoria);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPut.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPut);

        // Comprobamos si ha fallado
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }else{
            System.out.println("La actualización ha ido correcta.");
        }

        // Devolvemos el resultado
        String categoriaResult = readObject(response);
        return gson.fromJson(categoriaResult, Categoria.class);
    }

    // Método que nos sirve para la lectura de los JSON
    private static String readObject(HttpResponse httpResponse) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            char[] dataLength = new char[1024];
            int read;
            while ((read = bufferedReader.read(dataLength)) != -1) {
                buffer.append(dataLength, 0, read);
            }
            System.out.println(buffer.toString());
            return buffer.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }    
}
