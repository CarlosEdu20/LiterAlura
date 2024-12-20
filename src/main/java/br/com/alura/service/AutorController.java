package br.com.alura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class AutorController {
    @RestController
    @RequestMapping("/autores")
    public class Autor1Controller {

        @Autowired
        private AutorServiço autorService;

        @GetMapping
        public ResponseEntity<List<String>> listarAutores() {
            List<String> autoresFormatados = autorService.listarAutores();
            if (autoresFormatados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(autoresFormatados);
        }
    }

}
