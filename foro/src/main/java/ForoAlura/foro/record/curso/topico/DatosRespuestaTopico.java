package ForoAlura.foro.record.curso.topico;

import ForoAlura.foro.models.StatusTopico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico estado,
        Long autor,
        Long curso) {

}
