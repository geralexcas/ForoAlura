package ForoAlura.foro.models;

import ForoAlura.foro.record.curso.DatosActualizarCurso;
import ForoAlura.foro.record.curso.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cursoId;
    private String nombre;
    private String categoria;

    public Curso(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombre() != null) {
            this.nombre = datosActualizarCurso.nombre();
        }
        if (datosActualizarCurso.categoria() != null) {
            this.categoria = datosActualizarCurso.categoria();
        }
    }
}
