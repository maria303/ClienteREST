/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.clientecategoriasjson;

import com.fpmislata.domain.Cliente;
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

/**
 *
 * @author alumno
 */
public class ClienteClienteTest {
    public static void main(String[] args) throws IOException {
        // ***********************
        // *** LISTAR CLIENTE
        // ***********************     
        System.out.println("Lista de clientes del sistema");
        System.out.println("---------------------------");
        List<Cliente> lista = getListCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Clientes");
        for (Cliente cliente : lista) {
            System.out.println(cliente.toString());
        }
        System.out.println("----------------------------\n");

        // **********************************************
        // *** RECUPERAMOS UN CLIENTE EN CONCRETO
        // **********************************************
        System.out.println("Recuperando un cliente en concreto del sistema");
        Cliente c = getCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Cliente/findById/1");
        System.out.println("El cliente recuperado es: " + c.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** AÑADIMOS UN Cliente AL SISTEMA
        // **********************************************        
//        Cliente nuevoCliente = new Cliente();
//        nuevoCliente.setNombre("jose");
//        nuevoCliente.setApellidos("perez");
//        nuevoCliente.setNif("1234");
//        nuevoCliente.setDireccion("calle arriba");
//        nuevoCliente.setPoblacion("valencia");
//        nuevoCliente.setProvincia("valencia");
//        nuevoCliente.setCodigo_postal("6543");
//        nuevoCliente.setTelefono("98765432");
//
//        System.out.println("Insertando un nuevo cliente en el sistema");
//        Cliente c2 = addCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Cliente/add", nuevoCliente);
//        System.out.println("El cliente insertado es: " + c2.toString());
//        System.out.println("----------------------------\n");
        
        // **********************************************
        // *** ACTUALIZAMOS UN CLIENTE EN EL SISTEMA
        // **********************************************        
//        Cliente clienteExistente = new Cliente();
//        clienteExistente.setNombre("jose");
//        clienteExistente.setApellidos("perez");
//        clienteExistente.setNif("1234");
//        clienteExistente.setDireccion("calle arriba");
//        clienteExistente.setPoblacion("valencia");
//        clienteExistente.setProvincia("valencia");
//        clienteExistente.setCodigo_postal("6543");
//        clienteExistente.setTelefono("98765432");
//        
//        System.out.println("Modificando un cliente en el sistema");
//        Cliente c3 = updateCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Cliente/update/", clienteExistente);
//        System.out.println("El cliente modificado es ahora: " + c3.toString());
//        System.out.println("----------------------------\n");        

        
        // **********************************************
        // *** BORRAMOS UN CLIENTE DEL SISTEMA
        // **********************************************         
        System.out.println("Borrando un cliente en concreto del sistema");
        deleteCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Cliente/delete/3");
//        System.out.println("El cliente recuperado es: " + c.toString());
        System.out.println("----------------------------\n");   
    }
    
    // Obtenemos la lista de categorias
    private static List<Cliente> getListCliente(String url) throws IOException {
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
        Type type = new TypeToken<List<Cliente>>() {
        }.getType();
        // Parseamos el String lista a un objeto con el gson, devolviendo así un objeto List<Categoria>
        return gson.fromJson(lista, type);
    }

    // Obtenemos una categoria en concreto
    private static Cliente getCliente(String url) throws IOException {
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
        String clienteString = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el String categoria a un objeto con el gson, devolviendo así un objeto Categoria
        return gson.fromJson(clienteString, Cliente.class);
    }

    // Añadimos una categoria al sistema
    private static Cliente addCliente(String url, Cliente cliente) throws IOException {
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
        String jsonString = gson.toJson(cliente);
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

        String clienteResult = readObject(response);
        return gson.fromJson(clienteResult, Cliente.class);
    }
    
    // Borramos una categoria al sistema
    private static void deleteCliente(String url) {
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
                System.out.println("Se ha eliminado el cliente correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    private static Cliente updateCliente(String url, Cliente cliente) throws IOException {
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
        String jsonString = gson.toJson(cliente);
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
        String clienteResult = readObject(response);
        return gson.fromJson(clienteResult, Cliente.class);
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
