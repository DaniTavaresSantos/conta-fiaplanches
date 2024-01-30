package br.com.fiaplanchesclient.domain;

import java.util.Objects;

public class Client {

    private String id;

    private String cpf;

    private String nome;

    public Client(String id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client cliente = (Client) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf) && Objects.equals(nome, cliente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, nome);
    }
}
