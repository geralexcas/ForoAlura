package ForoAlura.foro.config.errores.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService {
    private final UsuarioRepository usuarioRepository;

    public AutenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository= usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UsuarioRepository.findByNombre(username);
    }

}
