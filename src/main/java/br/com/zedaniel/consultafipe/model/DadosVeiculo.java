package br.com.zedaniel.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosVeiculo(@JsonAlias("codigo") String codigo,
                           @JsonAlias("nome") String nome) {
}
