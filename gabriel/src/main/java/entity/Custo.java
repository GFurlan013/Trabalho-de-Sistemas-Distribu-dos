package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_custo;

    private String valor;

    @ManyToOne
    @JoinColumn(name = "projeto_id_projeto")
    private Projeto projeto;
}