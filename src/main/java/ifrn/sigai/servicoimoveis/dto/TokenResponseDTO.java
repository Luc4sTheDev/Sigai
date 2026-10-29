package ifrn.sigai.servicoimoveis.dto;

public class TokenResponseDTO {
    private final String token;
    private final String tipo;

    public TokenResponseDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() { return token; }
    public String getTipo() { return tipo; }
}