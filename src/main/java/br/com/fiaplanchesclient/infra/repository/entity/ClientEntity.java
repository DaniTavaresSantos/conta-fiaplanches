package br.com.fiaplanchesclient.infra.repository.entity;

import br.com.fiaplanchesclient.generated.Generated;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "Cliente")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Generated
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
