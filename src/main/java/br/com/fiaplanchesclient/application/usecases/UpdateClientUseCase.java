package br.com.fiaplanchesclient.application.usecases;

import br.com.fiaplanchesclient.application.ports.out.ClientRepositoryPortOut;
import br.com.fiaplanchesclient.domain.Client;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import br.com.fiaplanchesclient.infra.exception.handler.ContaBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
@Slf4j

public class UpdateClientUseCase {

    private final ClientRepositoryPortOut clientRepositoryPortOut;

    public UpdateClientUseCase(ClientRepositoryPortOut clientRepositoryPortOut) {
        this.clientRepositoryPortOut = clientRepositoryPortOut;
    }

    public ClientDto atualiza(ClientDto clienteDtoNew) {
        log.info("Atualizando cliente para o CPF: " + clienteDtoNew.cpf());
        ClientDto clienteDtoOld = clientRepositoryPortOut.findClientByCpf(clienteDtoNew.cpf()).orElseThrow(
                () -> new ContaBusinessException("Cliente nao encontrado.")
        );

        Client clienteNew = clienteDtoNew.toClient();
        Client clienteOld = clienteDtoOld.toClient();
        BeanUtils.copyProperties(clienteNew, clienteOld, "id");

        ClientDto clienteDtoAtualizado = ClientDto.toClientDto(clienteOld);

        var clienteDtoSaved = clientRepositoryPortOut.saveClient(clienteDtoAtualizado);
        log.info("Cliente atualizado com sucesso.");

        return clienteDtoSaved;
    }
}
