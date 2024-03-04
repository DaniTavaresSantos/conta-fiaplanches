package br.com.fiaplanchesclient.application.ports.out;

import br.com.fiaplanchesclient.infra.dto.ClientDto;

import java.util.Optional;

public interface ClientRepositoryPortOut {

    ClientDto saveClient(ClientDto clienteDto);

    Optional<ClientDto> findClientByCpf(String cpf);

    void deleteClient(String cpf);

    void deleteAll();
}
