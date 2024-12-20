package br.com.alura.model;

import java.util.List;

public record Gutendex(int count, String next, String previous, List<DadosLivros> results) {
}
