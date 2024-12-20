package br.com.alura;

import br.com.alura.service.RequestApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        RequestApi requestApi = new RequestApi();
        requestApi.request("https://gutendex.com/books");

    }

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

}
