package ForoAlura.foro.record.curso;

import ForoAlura.foro.models.Curso;

public record DatosListadoCurso(Long id, String nombre, String categoria) {
    public DatosListadoCurso(Curso curso) {
        this(curso.getCursoId(), curso.getNombre(), curso.getCategoria());
    }
}
