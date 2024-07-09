package ForoAlura.foro.record.curso.topico;

import ForoAlura.foro.models.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotNull
                Long id,
        String titulo,
        String mensaje,
        StatusTopico estado,
        Long autor,
        Long curso
) {
}
