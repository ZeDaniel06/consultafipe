package br.com.zedaniel.consultafipe.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private final String INICIO_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final String POS_TIPO = "/marcas/";
    private String tipoVeiculo;

    public void inicializar(){
        pedeVeiculo();
        String uri = INICIO_URL + tipoVeiculo + POS_TIPO;
        realizaRequisicao(uri);


    }

    public void pedeVeiculo(){
        try{
            System.out.println("----------------------------------");
            System.out.println("Que tipo de veículo você deseja pesquisar?");
            System.out.println("1 - Carro;\n" +
                    "2 - Caminhão\n" +
                    "3 - Moto;" +
                    "\nDigite o número correspondente: ");
            int menu;
            menu = 0;


            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu){
                case 1:
                    tipoVeiculo = "carros";
                    break;
                case 2:
                    tipoVeiculo = "caminhoes";
                    break;
                case 3:
                    tipoVeiculo = "motos";
                    break;
                default:
                    throw new InputMismatchException();
            }
        }catch (InputMismatchException e){
            System.out.println("Você deve digitar um número correspondente!");
            scanner.nextLine();
            pedeVeiculo();
        }


    }

    public void realizaRequisicao(String uri) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        try{
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }catch (IOException | InterruptedException e){
            System.out.println("Erro na requisição!");
        }
        System.out.println();

    }
}
