package br.com.zedaniel.consultafipe.principal;

import br.com.zedaniel.consultafipe.model.DadosMarca;
import br.com.zedaniel.consultafipe.model.DadosModelo;
import br.com.zedaniel.consultafipe.service.ConsumoApi;
import br.com.zedaniel.consultafipe.service.ConversorJson;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private final String INICIO_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final String POS_TIPO = "/marcas/";
    private final String POS_MARCA = "/modelos/";
    private String tipoVeiculo;
    private List<DadosMarca> listaMarcas;
    private List<DadosMarca> listaModelos;
    private int codigoMarca;

    public void inicializar(){
        ConsumoApi consumoApi = new ConsumoApi();
        pedeTipo();
        String uriMarcas = INICIO_URL + tipoVeiculo + POS_TIPO;
        String marcas = consumoApi.obterDados(uriMarcas);
        ConversorJson conversorJson = new ConversorJson();
        listaMarcas = conversorJson.obterLista(marcas, DadosMarca.class);

        listaMarcas = listaMarcas.stream()
                .sorted(Comparator.comparing(DadosMarca::nome))
                .collect(Collectors.toList());

        pedeMarca();

        String uriModelos = uriMarcas + codigoMarca + POS_MARCA;
        String modelos = consumoApi.obterDados(uriModelos);
        System.out.println(modelos);

        DadosModelo retornoModelo = conversorJson.obterDados(modelos, DadosModelo.class);
        listaModelos = retornoModelo.modelos();

        listaModelos = listaModelos.stream()
                .sorted(Comparator.comparing(DadosMarca::nome))
                .collect(Collectors.toList());

        pedeModelo();







    }

    public void pedeTipo(){
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

            pedeMarca();
        }


    }

    public void pedeMarca(){
        try{
            System.out.println("--------------------------------\nLista de marcas:\n");
            listaMarcas.forEach(m -> System.out.println("Nome: " + m.nome() +
                    ", Código: " + m.codigo()));

            System.out.println("---------------------------------\nDigite o código da marca que você deseja: ");
            int menu;
            menu = 0;

            /// //

            menu = scanner.nextInt();
            scanner.nextLine();
            Integer menuInteger = menu;


            Optional<DadosMarca> busca = listaMarcas.stream()
                    .filter(m -> m.codigo().equals(menuInteger))
                    .findFirst();

            if(busca.isEmpty())
                throw new InputMismatchException();
            else{
                this.codigoMarca = menu;
                return;
            }


        }catch (InputMismatchException e){
            System.out.println("Você deve digitar um número correspondente!");
            pedeMarca();
        }


    }

    public void pedeModelo(){
        try{
            System.out.println("--------------------------------\nLista de modelos:\n");
            listaModelos.forEach(m -> System.out.println("Nome: " + m.nome() +
                    ", Código: " + m.codigo()));

            System.out.println("---------------------------------\nDigite o código da marca que você deseja: ");
            int menu;
            menu = 0;

            /// //

            menu = scanner.nextInt();
            scanner.nextLine();
            Integer menuInteger = menu;


            Optional<DadosMarca> busca = listaMarcas.stream()
                    .filter(m -> m.codigo().equals(menuInteger))
                    .findFirst();

            if(busca.isEmpty())
                throw new InputMismatchException();
            else{
                this.codigoMarca = menu;
                return;
            }


        }catch (InputMismatchException e){
            System.out.println("Você deve digitar um número correspondente!");
            pedeMarca();
        }


    }


}
