package br.com.alura.principal;

import br.com.alura.model.DadosLivros;
import br.com.alura.model.Gutendex;
import br.com.alura.service.AutorServiço;
import br.com.alura.service.ConverteDados;
import br.com.alura.service.LivroService;
import br.com.alura.service.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private RequestApi consumoApi = new RequestApi();
    private ConverteDados converteDados = new ConverteDados();

    @Autowired
    private AutorServiço autorServiço;

    @Autowired
    private LivroService livroService;

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0) {
            var menu= """
                    ----------------------------
                                        Escolha o número de sua opção:
                                        1- buscar livro pelo título
                                        2- listar livros registrados
                                        3- listar autores registrados
                                        4- listar autores vivos em um determinado ano
                                        5- listar livros em um determinado idioma
                                        0- sair                                \s
                    ----------------------------
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroApi();
                    break;

                case 2:
                    listarLivrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;

                case 5:
                    listarPorLivrosIdiomas();
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarPorLivrosIdiomas() {

    }

    private void listarAutoresVivosEmDeterminadoAno() {


    }

    private void listarAutoresRegistrados() {
        List<String> autoresFormatados = AutorServiço;
        if (!autoresFormatados.isEmpty()) {
            for (String autor : autoresFormatados) {
                System.out.println(autor);
            }
        } else {
            System.out.println("Nenhum autor registrado.");
        }
    }


    private void listarLivrosRegistrados() {
        List<String> livrosFormatados = livroService.listarLivros();
        if (!livrosFormatados.isEmpty()) {
            for (String livro : livrosFormatados) {
                System.out.println(livro);
            }
        } else {
            System.out.println("Nenhum livro registrado.");
        }

    }

    private void buscarLivroApi() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = sc.nextLine();
        var json = consumoApi.request(ENDERECO + nomeLivro.replace(" ", "%20"));

        Gutendex dados = converteDados.obterDados(json, Gutendex.class);

        if (dados.results() != null && !dados.results().isEmpty()) {
            for (DadosLivros livro : dados.results()) {
                System.out.println(livro);

                if (livroService.buscarPorTitulo(livro.titulo()).isEmpty()) {
                    livroService.salvarLivro(livro);
                } else {
                    System.out.println("O livro '" + livro.titulo() + "' já está registrado no banco de dados.");
                }
            }
        } else {
            System.out.println("Nenhum resultado encontrado.");
        }
    }


}
