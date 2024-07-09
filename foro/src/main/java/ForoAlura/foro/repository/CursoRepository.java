package ForoAlura.foro.repository;

import ForoAlura.foro.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CursoRepository extends JpaRepository<Curso, Long> {

}
