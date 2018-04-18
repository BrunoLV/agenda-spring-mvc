package br.com.valhala.agenda.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contato")
@NamedEntityGraph(name = "comTelefones", attributeNodes = {@NamedAttributeNode("telefones")})
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contato", orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();

    public Contato() {
        super();
    }

    public Contato(Long id, String nome, List<Telefone> telefones) {
        super();
        this.id = id;
        this.nome = nome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Contato [");
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
        if (nome != null) {
            builder.append("nome=").append(nome).append(", ");
        }
        if (telefones != null) {
            builder.append("telefones=").append(telefones);
        }
        builder.append("]");
        return builder.toString();
    }

}
