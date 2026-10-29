package ifrn.sigai.servicoimoveis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifrn.sigai.servicoimoveis.dto.LoginRequestDTO;
import ifrn.sigai.servicoimoveis.dto.TokenResponseDTO;
import ifrn.sigai.servicoimoveis.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            
        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        String role = user.getAuthorities().iterator().next().getAuthority();
        String token = jwtService.gerarToken(dto.getUsername(), role);
        return ResponseEntity.ok(new TokenResponseDTO(token, "Bearer"));
    }
}

