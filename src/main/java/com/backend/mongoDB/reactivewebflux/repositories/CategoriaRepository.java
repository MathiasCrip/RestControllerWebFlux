package com.backend.mongoDB.reactivewebflux.repositories;

import com.backend.mongoDB.reactivewebflux.documents.Categoria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

//Modelo de acceso a Datos de categoria.
// extendemos de ReactiveCrudRepository para implementar las operaciones CRUD

/*No hace falta anotar con @Repository o @Component, ya que al ser una interfaz propia de Spring. Ya está
 * alojada en el contenedor de independencia. En este caso, igualmente, la anotamos*/
@Repository
public interface CategoriaRepository extends ReactiveCrudRepository<Categoria, String> {
}
