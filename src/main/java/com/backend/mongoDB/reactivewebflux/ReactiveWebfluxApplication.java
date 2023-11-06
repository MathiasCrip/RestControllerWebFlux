package com.backend.mongoDB.reactivewebflux;

import com.backend.mongoDB.reactivewebflux.documents.Categoria;
import com.backend.mongoDB.reactivewebflux.documents.Producto;
import com.backend.mongoDB.reactivewebflux.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

/*CLASE PRINCIPAL. Implementamos CommandLineRunner e implementamos el metodo run(String...args)*/
@SpringBootApplication
public class ReactiveWebfluxApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactiveWebfluxApplication.class);

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    /*Es una clase de Spring Data MongoDB que implementa la interfaz ReactiveMongoOperations.
    * Realiza operaciones sobre las colecciones. En este caso, la utilizamos para borrar la coleccion cada vez
    * que corramos la App. Ya que si no la borramos al comenzar, se seguirian agreagando los nuevos registros,
    * sobre los viejos.*/

    @Autowired
    private ProductoService productoService;
    //Inyectamos la Interfaz productoService

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebfluxApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //Borramos las colleciones.Antes de cargar los datos
        mongoTemplate.dropCollection("productos").subscribe();
        mongoTemplate.dropCollection("categorias").subscribe();

/*subscribe() ->método que se utiliza para suscribirse a un flujo de datos. Cuando se llama a este método,
        se activa el flujo de datos y se comienza a recibir los eventos emitidos por el observable */

//      Instanciamos las categorias, en este caso, cuatro
        Categoria electronico = new Categoria("Electrónico");
        Categoria deporte = new Categoria("Deporte");
        Categoria computacion = new Categoria("Computación");
        Categoria mueble = new Categoria("Mueble");

// Flux-> Los elementos se emiten a medida que están disponibles y se procesan de forma asíncrona a medida que se reciben.
        Flux.just(electronico, deporte, computacion, mueble)
                .flatMap(productoService::crearCategoria)//Usamos flatMap para guardar las categorias dentro de productoService
                .thenMany(
                        //thenMany -> significa "Entonces..", cuando termina el flujo anterior,a continuacion, se cargan los datos de los productos
                        Flux.just(new Producto("TV Panasonic LED 50''", 2559.99, electronico),
                                        new Producto("Pelota YPF", 120.40, deporte),
                                        new Producto("Mesa cuadrada ROBLE", 25.99, mueble),
                                        new Producto("Bicicleta Deportiva Sochia", 269.79, deporte),
                                        new Producto("Celular MOTOROLA G31", 853.39, electronico),
                                        new Producto("Tablet HITACHI T850", 380.99, computacion),
                                        new Producto("Sillas CEDRO X unidad", 12.30, mueble),
                                        new Producto("Triciclo FORCE", 35.19, deporte),
                                        new Producto("ASUS Notebook X255''", 860.90, computacion),
                                        new Producto("Microondas SAMSUMG''", 580.00, electronico))
                                .flatMap(p -> {//aplanamos el flujo, es decir, el flujo Flux.just(),emite tipos de Flux<Producto>. Con flatMap, aplanamos ese flujo a productos
                                    p.setCreateAt(new Date()); //seteamos la fecha de creación del registro
                                    return productoService.crear(p); //guardamos el producto
                                })).subscribe(pr -> log.info("Producto creado: " + pr.getNombre() + ", Categoria: " + pr.getCategoria().getNombre())
                        , error -> log.error("No se pueden cargar nombres vacios.")
                        , () -> log.info("Productos guardados exitosamente."));
        //MUY IMPORTANTE SUSCRIBIRSE AL FLUJO, ya que si no nos suscribimos, no sucede nada.

        /*
        subscribe, puede recibir 3 parametros.
        El 1ro un consumer de tipo producto.
        El 2do un consumer de tipo Throwable, osea, que maneje el error, en caso que dentro de todo_ el flujo anterior
        EL 3ro, onComplete -> metodo que se invoca si se completa el flujo anterior sin ningun problema */
    }
}
