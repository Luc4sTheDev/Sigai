package com.example.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 

@Table (name = "imoveis")
public class Imovel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String endereco;
    private Double valorAluguel;
    private String descricao;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "detalhes_id", referencedColumnName = "id")
    private DetalhesImoveis detalhes;

    @ManyToMany 
    @JoinTable(
        name = "Imovel_comodidade",
        joinColumns = @JoinColumn (name = "imovel_id"),
        inverseJoinColumns = @JoinColumn (name = "comodidade_id")
    )

    private List<Comodidade> comodidades = new ArrayList<>();

    @OneToMany (mappedBy = "imovel")
    private List<Foto> fotos = new ArrayList<>();
}
