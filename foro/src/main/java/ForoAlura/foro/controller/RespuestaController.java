package ForoAlura.foro.controller;

import ForoAlura.foro.config.errores.TratarErrores;
import ForoAlura.foro.models.Respuesta;
import ForoAlura.foro.models.Topico;
import ForoAlura.foro.models.Usuario;
import ForoAlura.foro.record.curso.respuesta.DatosActualizarRespuesta;
import ForoAlura.foro.record.curso.respuesta.DatosListadoRespuesta;
import ForoAlura.foro.record.curso.respuesta.DatosRegistroRespuesta;
import ForoAlura.foro.record.curso.respuesta.DatosRespuestaRespuesta;
import ForoAlura.foro.repository.RespuestaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/respuesta")
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    public RespuestaController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                      UriComponentsBuilder uriComponentsBuilder) {
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(
                respuesta.getRespuestaId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getTopicoId()).getTopicoId(),
                new Usuario(respuesta.getAutor().getUsuarioId()).getUsuarioId()
        );
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getRespuestaId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuesta(@PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/topico/{topico}")
    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuestaPorTopico(@PathVariable Long topico,
                                                                                 @PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorTopico(topico, paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuestaPorAutor(@PathVariable Long autor,
                                                                                @PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorAutor(autor, paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> RetornarDatosRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var datosRespuesta = new DatosListadoRespuesta(
                respuesta.getRespuestaId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(
                respuesta.getRespuestaId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getTopicoId()).getTopicoId(),
                new Usuario(respuesta.getAutor().getUsuarioId()).getUsuarioId())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        if (respuestaRepository.existsById(id)) {
            Respuesta respuesta = respuestaRepository.getReferenceById(id);
            respuestaRepository.delete(respuesta);
            return ResponseEntity.noContent().build();
        }
        return new TratarErrores().tratarError404();
    }
}
