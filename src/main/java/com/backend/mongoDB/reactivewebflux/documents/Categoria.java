package com.backend.mongoDB.reactivewebflux.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*Clase coleccion*/

//Marcamos con @Document para que se mapee a la base de datos NoSQL
@Document(collection = "categorias")
public class Categoria {

    @Id
    private String id;
    private String nombre;


    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria() {
    }

    public String getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
