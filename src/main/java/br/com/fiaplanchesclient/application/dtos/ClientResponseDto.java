package br.com.fiaplanchesclient.application.dtos;

import br.com.fiaplanchesclient.infra.dto.ClientDto;

public record ClientResponseDto(
        String id,
        String cpf,
        String nome
) {

    public static ClientResponseDto toClientResponseDto(ClientDto clienteDto) {
        return new ClientResponseDto(
                clienteDto.id(),
                clienteDto.cpf(),
                clienteDto.nome()
        );
    }
}
