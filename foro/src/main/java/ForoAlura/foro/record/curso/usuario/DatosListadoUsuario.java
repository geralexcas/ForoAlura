package ForoAlura.foro.record.curso.usuario;

import ForoAlura.foro.models.Usuario;

public record DatosListadoUsuario(Long id, String nombre, String email) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getUsuarioId(), usuario.getNombre(), usuario.getEmail());
    }
}
