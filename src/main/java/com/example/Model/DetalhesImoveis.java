package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "detalhes_imovel")
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class DetalhesImoveis {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

   private String descricaoCompleta;
   private Integer areaM2;
   private Integer vagasGaragem;


   @OneToOne (mappedBy = "detalhes")
   private Imovel imovel;
}
