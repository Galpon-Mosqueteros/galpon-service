package galpon.galponservice.bird.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.time.LocalDate;
import java.util.Date;

public record BirdResource(
        Long id, String placa, String nombre, TipoAve tipo,
        String color, Double peso, EstadoAve estado,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fechaNacimiento,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fechaMuerte,
        String placaPadre, String placaMadre) {
}
