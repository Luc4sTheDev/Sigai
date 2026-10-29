package ifrn.sigai.servicoimoveis.repository;

import ifrn.sigai.servicoimoveis.model.Imovel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    List<Imovel> findByEnderecoContainingIgnoreCase(String endereco);
    List<Imovel> findByValorAluguelLessThanEqual(Double valorMaximo);
    List<Imovel> findByEnderecoContainingIgnoreCaseAndValorAluguelLessThanEqual(String endereco, Double valorMaximo);

}
