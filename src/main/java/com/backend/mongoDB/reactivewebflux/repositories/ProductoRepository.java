package com.backend.mongoDB.reactivewebflux.repositories;

import com.backend.mongoDB.reactivewebflux.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

//Modelo de acceso a Datos de Producto.
// extendemos de ReactiveCrudRepository para implementar las operaciones CRUD

/*No hace falta anotar con @Repository o @Component, ya que al ser una interfaz propia de Spring. Ya está
 * alojada en el contenedor de independencia. En este caso, igualmente, la anotamos*/
@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto,String> {
}
