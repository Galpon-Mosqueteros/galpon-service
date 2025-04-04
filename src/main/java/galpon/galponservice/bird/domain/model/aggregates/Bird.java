package galpon.galponservice.bird.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import galpon.galponservice.bird.domain.model.commands.CreateBirdCommand;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.observation.domain.model.aggregates.Observation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.text.Bidi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Entity
@Table(name = "ave")
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 50)
    private String placa;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAve tipo;

    @NotBlank
    @Column(nullable = false)
    private String color;

    @NotNull
    @Column(nullable = false)
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAve estado;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaMuerte;

    @ManyToOne
    @JoinColumn(name = "placa_padre", referencedColumnName = "placa", nullable = true, foreignKey = @ForeignKey(name = "fk_ave_padre"))
    private Bird padre;

    @ManyToOne
    @JoinColumn(name = "placa_madre", referencedColumnName = "placa", nullable = true, foreignKey = @ForeignKey(name = "fk_ave_madre"))
    private Bird madre;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ave_usuario"))
    private User usuario;

    @OneToMany(mappedBy = "ave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations = new ArrayList<>();

    public List<Observation> getObservations() {
        return observations;
    }

    public Bird(String placa, String nombre, TipoAve tipo, String color, Double peso,
                EstadoAve estado, LocalDate fechaNacimiento, LocalDate fechaMuerte, Bird padre, Bird madre, User usuario) {
        this.placa = placa;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.peso = peso;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
        this.padre = padre;
        this.madre = madre;
        this.usuario = usuario;
    }

    public Bird(CreateBirdCommand command, User usuario, Bird padre, Bird madre){
        this.placa = command.placa();
        this.nombre = command.nombre();
        this.tipo = command.tipo();
        this.color = command.color();
        this.peso = command.peso();
        this.estado = command.estado();
        this.fechaNacimiento = command.fechaNacimiento();
        this.fechaMuerte = command.fechaMuerte();
        this.usuario = usuario;
        this.padre = padre;
        this.madre = madre;
    }


    public Bird updateInformation(String placa, String nombre, TipoAve tipo, String color, Double peso,
                                  EstadoAve estado, LocalDate fechaNacimiento, LocalDate fechaMuerte) {
        this.placa = placa;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.peso = peso;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
        return this;
    }

    public Bird() {}

    public Long getId() { return id; }
    public String getPlaca() { return placa; }
    public String getNombre() { return nombre; }
    public TipoAve getTipo() { return tipo; }
    public String getColor() { return color; }
    public Double getPeso() { return peso; }
    public EstadoAve getEstado() { return estado; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaMuerte() { return fechaMuerte; }
    public Bird getPadre() { return padre; }
    public Bird getMadre() { return madre; }
    public Long getUsuario() { return usuario != null ? usuario.getId() : null; }

    public void setPadre(Bird padre) { this.padre = padre; }
    public void setMadre(Bird madre) { this.madre = madre; }
}
