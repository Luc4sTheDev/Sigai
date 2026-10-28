package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.Imovel;
import java.util.List;


public interface ImovelRepository extends JpaRepository<Imovel, Long> {
   List<Imovel> findByEndereco(String endereco);
   List<Imovel> findByValorAluguelLessThan(Double valor);
   List<Imovel> findByEnderecoContainingIgnoreCase(String trecho);
}
