package br.com.fiaplanchesclient.application.usecases;

import br.com.fiaplanchesclient.application.ports.out.ClientRepositoryPortOut;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import br.com.fiaplanchesclient.infra.exception.handler.ContaBusinessException;

public class CreateClientUseCase {

    private final ClientRepositoryPortOut clientRepositoryPortOut;

    public CreateClientUseCase(ClientRepositoryPortOut clientRepositoryPortOut) {
        this.clientRepositoryPortOut = clientRepositoryPortOut;
    }

    public ClientDto cadastra(ClientDto clienteDto) {
        var cliente = clientRepositoryPortOut.findClientByCpf(clienteDto.cpf());
        if (cliente.isEmpty()) {
            return clientRepositoryPortOut.saveClient(clienteDto);
        } else {
            throw new ContaBusinessException("Cliente ja cadastrado");
        }
    }
}
