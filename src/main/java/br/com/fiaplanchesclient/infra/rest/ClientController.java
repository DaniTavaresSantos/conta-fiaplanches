package br.com.fiaplanchesclient.infra.rest;

import br.com.fiaplanchesclient.application.dtos.ClientRequestDto;
import br.com.fiaplanchesclient.application.dtos.ClientResponseDto;
import br.com.fiaplanchesclient.application.usecases.CreateClientUseCase;
import br.com.fiaplanchesclient.application.usecases.DeleteClientUseCase;
import br.com.fiaplanchesclient.application.usecases.FindClientUseCase;
import br.com.fiaplanchesclient.application.usecases.UpdateClientUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe responsável por expor os endpoints de clientes.
 */
@RestController
@RequestMapping("/v1/client")
@Slf4j
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase,
                            FindClientUseCase findClientUseCase,
                            UpdateClientUseCase updateClientUseCase,
                            DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.findClientUseCase = findClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ClientResponseDto> criarCliente(@RequestBody @Valid ClientRequestDto clienteRequestDto,
                                                          UriComponentsBuilder uriBuilder){
        log.info("Executando criarCliente");
        var clienteDto = createClientUseCase.cadastra(clienteRequestDto.toClienteDto());
        var clienteResponse = ClientResponseDto.toClientResponseDto(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping(path = "find/{cpf}")
    public ResponseEntity<ClientResponseDto> buscaClienteCpf(
            @PathVariable
            @NotBlank(message = "Número do CPF não pode ser vazio")
            @CPF(message = "CPF informado invalido")
            String cpf
    ) {
        return ResponseEntity.ok(ClientResponseDto.toClientResponseDto(findClientUseCase.procuraPorCpf(cpf)));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ClientResponseDto> updateCliente(@RequestBody @Valid ClientRequestDto updateCliente) {
        return ResponseEntity.ok(ClientResponseDto.toClientResponseDto(updateClientUseCase.atualiza(updateCliente.toClienteDto())));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> removeCliente(
            @PathVariable
            @NotBlank(message = "Número do CPF não pode ser vazio")
            @CPF(message = "CPF informado invalido")
            String cpf
    ) {
        deleteClientUseCase.remove(cpf);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }
}
