package br.unitins.topicos1.bone.dto;

<<<<<<< HEAD
import br.unitins.topicos1.bone.model.Perfil;
=======
import java.util.List;
import java.util.stream.Collectors;

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import br.unitins.topicos1.bone.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String login,
<<<<<<< HEAD
    Perfil perfil
=======
    List<PedidoDTOResponse> pedidos
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getLogin(),
<<<<<<< HEAD
            usuario.getPerfil()
=======
            usuario.getPedidos() != null
                ? usuario.getPedidos()
                    .stream()
                    .map(PedidoDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        );
    }
}
