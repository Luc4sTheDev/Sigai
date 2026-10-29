package ifrn.sigai.servicoimoveis.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "imoveis")
@Data 
public class Imovel {

    public Imovel() {
        super();
    }

    public Imovel(Long id, String endereco, Double valorAluguel, String descricao) {
        this.id = id;
        this.endereco = endereco;
        this.valorAluguel = valorAluguel;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "imovel", fetch = FetchType.LAZY)
    private List<Foto> fotos = new ArrayList<>();

    private String endereco;
    private Double valorAluguel;
    private String descricao;
   
}
