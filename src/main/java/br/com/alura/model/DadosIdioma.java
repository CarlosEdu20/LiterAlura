package br.com.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public record DadosIdioma(@JsonAlias("languages") String idioma) {


}