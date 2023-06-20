package com.api.bicicletario.service;

import com.api.bicicletario.dao.CartaoDAO;
import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.enumerator.CiclistaStatus;
import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.model.Ciclista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.api.bicicletario.util.Constantes.DADOS_ALTERADOS_SUCESSO;
import static com.api.bicicletario.util.Constantes.ERRO_ATIVAR_CICLISATA;
import static com.api.bicicletario.util.Constantes.ERRO_CADASTRAR_CICLISTA;
import static com.api.bicicletario.util.Constantes.ERRO_RECUPERAR_CICLISTA;
import static com.api.bicicletario.util.Constantes.MENSAGEM_ATIVACAO_CADASTRO;

@Service
public class CiclistaService {

    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CiclistaDAO dao;

    @Autowired
    private CartaoDAO cartaoDAO;

    public void cadastrarCiclista(CadastrarCiclistaDTO cadastrarCiclistaDTO) {
        if(cadastrarCiclistaDTO == null) {
            throw new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA);
        }

        if(cadastrarCiclistaDTO.getCiclista() == null || cadastrarCiclistaDTO.getCartaoDeCredito() == null) {
            throw new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA);
        }

        Ciclista ciclista = cadastrarCiclistaDTO.getCiclista();
        CartaoDeCredito cartaoDeCredito = cadastrarCiclistaDTO.getCartaoDeCredito();

        // [A1] Email já cadastrado ou inválido
        if(!emailService.emailValido(ciclista.getEmail())) {
            throw new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA);
        }
        // [A3] Cartão reprovado
        if(!cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito)) {
            throw new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA);
        }
        // [A2] Dados inválidos
        this.validarDados(ciclista);

        ciclista.setStatus(CiclistaStatus.AGUARDANDO_CONFIRMACAO);

        dao.salvarCiclista(ciclista);
        emailService.enviarEmail(ciclista.getEmail(), MENSAGEM_ATIVACAO_CADASTRO);
    }

    private void validarDados(Ciclista ciclista) {
        if (ciclista.getNome() == null
                || ciclista.getNascimento() == null
                || ciclista.getCpf() == null
                || ciclista.getPassaporte() == null
                || ciclista.getNacionalidade () == null
                || ciclista.getEmail() == null
                || ciclista.getUrlFotoDocumento() == null) {
            throw new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA);
        }
    }

    public void ativarCiclista(Long idCiclista) {
        Ciclista ciclista = recuperarCiclista(idCiclista);
        if(!registroPendente(ciclista.getStatus())) {
            throw new IllegalArgumentException(ERRO_ATIVAR_CICLISATA);
        }

        ciclista.setStatus(CiclistaStatus.ATIVO);
    }

    public void alterarCiclista(Ciclista ciclista) {
        dao.alterarCiclista(ciclista);
        emailService.enviarEmail(ciclista.getEmail(), DADOS_ALTERADOS_SUCESSO);
    }

    public Ciclista recuperarCiclista(Long idCiclista) {
        if(idCiclista == null) {
            throw new IllegalArgumentException(ERRO_RECUPERAR_CICLISTA);
        }

        return dao.recuperarCiclista(idCiclista);
    }

    private boolean registroPendente(CiclistaStatus status) {
        return true;
    }

}
