package br.com.zedaniel.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DadosModelo( @JsonAlias("modelos") List<DadosMarca> modelos) {
}
