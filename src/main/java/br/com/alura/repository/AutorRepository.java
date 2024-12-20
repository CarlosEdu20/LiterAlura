package br.com.alura.repository;

import br.com.alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
    @Query("SELECT a FROM Autor a JOIN FETCH a.livros")
    List<Autor> findAllWithLivros();

}