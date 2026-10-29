package ifrn.sigai.servicoimoveis.exception;

public class ImovelNaoEncontradoException extends RuntimeException {
    
    public ImovelNaoEncontradoException(Long id) {
        super("Imóvel não encontrado: " + id);
    }
    
}