package br.com.fiaplanchesclient.infra.dto;

import br.com.fiaplanchesclient.domain.Client;

public record ClientDto(
        String id,
        String cpf,
        String nome
) {

    public static ClientDto toClientDto(Client cliente) {
        return new ClientDto(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome()
        );
    }

    public Client toClient() {
        return new Client(
                this.id,
                this.cpf,
                this.nome
        );
    }

}
