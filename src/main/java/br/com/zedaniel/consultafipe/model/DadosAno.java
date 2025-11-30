package br.com.zedaniel.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAno(@JsonAlias("AnoModelo") Integer anoModelo,
                       @JsonAlias("Modelo") String modelo,
                       @JsonAlias("Marca") String marca,
                       @JsonAlias("Valor") String valor) {
}
