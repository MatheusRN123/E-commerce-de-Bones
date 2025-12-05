package br.unitins.topicos1.bone.dto;

<<<<<<< HEAD
import br.unitins.topicos1.bone.model.Perfil;
=======
import java.util.List;
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613

public record UsuarioDTO(
    String nome,
    String login,
    String senha,
<<<<<<< HEAD
    Perfil perfil
=======
    List<Long> idsPedidos
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
) {}