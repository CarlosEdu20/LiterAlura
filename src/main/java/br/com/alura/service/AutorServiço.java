package br.com.alura.service;

import br.com.alura.model.Autor;
import br.com.alura.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AutorServiço {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public List<String> listarAutores() {
        // Obtém todos os autores do banco de dados
        List<Autor> autores = autorRepository.findAll();

        // Formata a lista de autores
        return autores.stream().map(autor -> {
            // Obtém os livros associados ao autor
            String livros = autor.getLivros().stream()
                    .map(livro -> livro.getTitulo())
                    .collect(Collectors.joining(", "));

            // Formata as informações do autor
            return "-------------- Autor ----------------\n" +
                    "Nome: " + autor.getNome() + "\n" +
                    "Ano de nascimento: " + autor.getAnoNascimento() + "\n" +
                    "Ano de falecimento: " +
                    (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A") + "\n" +
                    "Livros: " + (livros.isEmpty() ? "Nenhum livro registrado" : livros) + "\n" +
                    "--------------------------------------";
        }).collect(Collectors.toList());
    }




}
