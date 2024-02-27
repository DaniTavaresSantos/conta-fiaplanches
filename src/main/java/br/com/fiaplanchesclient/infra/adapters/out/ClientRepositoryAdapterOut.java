package br.com.fiaplanchesclient.infra.adapters.out;

import br.com.fiaplanchesclient.application.ports.out.ClientRepositoryPortOut;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import br.com.fiaplanchesclient.infra.repository.MongoClientRepository;
import br.com.fiaplanchesclient.infra.repository.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryAdapterOut implements ClientRepositoryPortOut {

    private final MongoClientRepository mongoClientRepository;

    public ClientRepositoryAdapterOut(MongoClientRepository mongoClientRepository) {
        this.mongoClientRepository = mongoClientRepository;
    }

    @Override
    public ClientDto saveClient(ClientDto clienteDto) {
        var clienteEntity = mongoClientRepository.save(new ClientEntity(clienteDto));
        return clienteEntity.toClienteDto();
    }

    @Override
    public Optional<ClientDto> findClientByCpf(String cpf) {
        return mongoClientRepository.findByCpf(cpf).map(ClientEntity::toClienteDto);
    }

    @Override
    public void deleteClient(String cpf) {
        mongoClientRepository.deleteByCpf(cpf);
    }

    @Override
    public void deleteAll() {

        mongoClientRepository.deleteAll();
    }
}
