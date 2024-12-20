package br.com.alura.repository;

import br.com.alura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro>  findByTitulo(String titulo);

    List<Livro> findByIdioma(String idioma);

    @Query("SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.idioma = :idioma")
    List<Livro> findByIdiomaWithAutores(@Param("idioma") String idioma );
}
