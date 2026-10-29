package ifrn.sigai.servicoimoveis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ifrn.sigai.servicoimoveis.model.Role;
import ifrn.sigai.servicoimoveis.model.Usuario;
import ifrn.sigai.servicoimoveis.repository.UsuarioRepository;

@Component 
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            return; // se já tem usuário, não duplica
        }

        Usuario admin = new Usuario("admin", passwordEncoder.encode("admin123"), Role.ADMIN);
        usuarioRepository.save(admin);

        Usuario corretor = new Usuario("corretor", passwordEncoder.encode("corretor123"), Role.CORRETOR);
        usuarioRepository.save(corretor);

        Usuario cliente = new Usuario("cliente", passwordEncoder.encode("cliente123"), Role.CLIENTE);
        cliente.setRole(Role.CLIENTE);
        usuarioRepository.save(cliente);

        System.out.println(">>> Usuários de teste criados: admin/admin123, corretor/corretor123, cliente/cliente123");
    }
}