package br.com.zedaniel.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosMarca(@JsonAlias("codigo") Integer codigo,
                         @JsonAlias("nome") String nome) {
}
