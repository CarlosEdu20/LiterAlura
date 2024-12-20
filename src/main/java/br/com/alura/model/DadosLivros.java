package br.com.alura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DadosAutor> autor,
                          @JsonAlias ("languages") List<String> idioma,
                          @JsonAlias("download_count") Integer numeroDownloads) {
    @Override
    public String toString() {
        String autores = autor.stream()
                .map(DadosAutor::nome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");

        return "-------------- Livro ----------------\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autores + "\n" +
                "Idioma: " + String.join(", ", idioma) + "\n" +
                "Número de Downloads: " + numeroDownloads + "\n" +
                "--------------------------------------";
    }
}
