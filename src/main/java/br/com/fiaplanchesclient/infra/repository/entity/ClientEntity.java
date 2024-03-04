package br.com.fiaplanchesclient.infra.repository.entity;

import br.com.fiaplanchesclient.generated.Generated;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Generated
public class ClientEntity {

    @Id
    private String id;

    private String cpf;

    private String nome;

    public ClientEntity(ClientDto clienteDto) {
        this.id = clienteDto.id();
        this.cpf = clienteDto.cpf();
        this.nome = clienteDto.nome();
    }

    public ClientDto toClienteDto() {
        return new ClientDto(
                this.id,
                this.cpf,
                this.nome
        );
    }
}
