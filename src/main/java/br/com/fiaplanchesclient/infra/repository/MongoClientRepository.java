package br.com.fiaplanchesclient.infra.repository;

import br.com.fiaplanchesclient.infra.repository.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface MongoClientRepository extends MongoRepository<ClientEntity, String> {

    Optional<ClientEntity> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}
