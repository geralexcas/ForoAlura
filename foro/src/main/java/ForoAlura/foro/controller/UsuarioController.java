package ForoAlura.foro.controller;

import ForoAlura.foro.config.errores.TratarErrores;
import ForoAlura.foro.models.Usuario;
import ForoAlura.foro.record.curso.usuario.DatosActualizarUsuario;
import ForoAlura.foro.record.curso.usuario.DatosListadoUsuario;
import ForoAlura.foro.record.curso.usuario.DatosRegistroUsuario;
import ForoAlura.foro.record.curso.usuario.DatosRespuestaUsuario;
import ForoAlura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getUsuarioId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuario(@PageableDefault(size = 10, sort = "usuarioId") Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> RetornarDatosUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getEmail())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuarioRepository.delete(usuario);
            return ResponseEntity.noContent().build();
        }
        return new TratarErrores().tratarError404();
    }
}
