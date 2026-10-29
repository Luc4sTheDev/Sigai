package ifrn.sigai.servicoimoveis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.sigai.servicoimoveis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
