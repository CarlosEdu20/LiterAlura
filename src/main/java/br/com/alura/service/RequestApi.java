package br.com.alura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestApi {

    public String request(String api) {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        }
        catch (IOException | InterruptedException e) {
            System.out.println("Erro ao fazer a requisição " + e.getMessage());
            e.printStackTrace();

        }


        return null;





    }
}
