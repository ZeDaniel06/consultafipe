package br.com.zedaniel.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarca(@JsonAlias("codigo") Integer codigo,
                         @JsonAlias("nome") String nome) {
}
