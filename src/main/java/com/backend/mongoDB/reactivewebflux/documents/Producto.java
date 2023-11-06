package com.backend.mongoDB.reactivewebflux.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.Date;

/*Clase coleccion*/
//Marcamos con @Document para que se mapee a la base de datos NoSQL
@Document(collection = "productos")
public class Producto {

    @Id
    private String id;
    private String nombre;
    private Double precio;

    //    Relacionamos Categoria
    private Categoria categoria;
    private Date createAt;

    public Producto() {
        categoria = new Categoria();
    }

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
//    creamos un constructor con dos parametros y dentro llamamos al constructor de arriba con this(nombre,precio)

    public Producto(String nombre, Double precio, Categoria categoria) {
        this(nombre, precio);
        this.categoria = categoria;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
