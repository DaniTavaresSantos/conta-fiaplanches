# language: pt

Funcionalidade: Criar Cliente na base de Dados

  Cenario: : 01 - Criação de cliente na base com sucesso
    Dado o cliente de CPF 38037985822 e nome "Daniel"
    Quando for realizada a chamada no endpoint de criação de cliente
    Entao o cliente deve ser localizado com sucesso na base

  Cenario: : 02 - Buscar cliente na base com sucesso
    Dado o cliente de CPF 38037984850 e nome "Daniel Tavares"
    Quando for realizada a chamada no endpoint de busca de cliente
    Entao o cliente deve ser localizado com sucesso na base

  Cenario: : 03 - Atualizar cliente na base com sucesso
    Dado o cliente de CPF 49174699881 e nome "Nicole Tavares"
    E alterar os dados para CPF 49174699881 e nome Fernanda
    Quando for realizada a chamada no endpoint de atualizacao de cliente
    Entao o cliente deve ser atualizado com sucesso na base

  Cenario: : 04 - Delecao de cliente na base com sucesso
    Dado o cliente de CPF 10640145850 e nome "Cristian Macedo"
    Quando for realizada a chamada no endpoint de busca de cliente
    E for realizada a chamada no endpoint de delecao de cliente
    Entao o cliente não deve ser encontrado na base