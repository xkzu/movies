package cl.duoc.app.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //le indicamos a springboot que maneje esta clase como RestController
public class MovieController {

    @GetMapping("/healthy") //indicamos que este metodo será mapeado como tipo Get
    public ResponseEntity<String> getHealthy() {
        return ResponseEntity.ok("OK");
    }

}
