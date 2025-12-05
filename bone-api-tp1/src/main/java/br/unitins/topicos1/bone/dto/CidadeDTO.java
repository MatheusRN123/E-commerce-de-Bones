package br.unitins.topicos1.bone.dto;

<<<<<<< HEAD

public record CidadeDTO(
    String nome,
    Long idEstado
=======
import java.util.List;

public record CidadeDTO(
    String nome,
    Long idEstado,
    List<Long> idEnderecos
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
) {}
