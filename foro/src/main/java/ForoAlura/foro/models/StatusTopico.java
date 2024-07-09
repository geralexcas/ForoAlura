package ForoAlura.foro.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public enum StatusTopico {

    NO_RESPONDIDO,
    NO_SOLUCIONADO,
    SOLUCIONADO,
    CERRADO;
}
