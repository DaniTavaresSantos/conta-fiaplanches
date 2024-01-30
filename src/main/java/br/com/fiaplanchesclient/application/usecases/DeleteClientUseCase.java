package br.com.fiaplanchesclient.application.usecases;

import br.com.fiaplanchesclient.application.ports.out.ClientRepositoryPortOut;
import br.com.fiaplanchesclient.infra.exception.handler.ContaBusinessException;

public class DeleteClientUseCase {


    private final ClientRepositoryPortOut clientRepositoryPortOut;

    public DeleteClientUseCase(ClientRepositoryPortOut clientRepositoryPortOut) {
        this.clientRepositoryPortOut = clientRepositoryPortOut;
    }

    public void remove(String cpf) {
        clientRepositoryPortOut.findClientByCpf(cpf)
                .orElseThrow(() -> new ContaBusinessException("Cliente não encontrado"));
        clientRepositoryPortOut.deleteClient(cpf);
    }
}
