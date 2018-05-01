package br.com.valhala.agenda.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.valhala.agenda.modelo.enums.EnumTipoTelefone;

@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String ddd;
    private String numero;

    @Enumerated(EnumType.STRING)
    private EnumTipoTelefone tipo;

    @ManyToOne
    @JoinColumn(name = "id_contato")
    private Contato contato;

    public Telefone() {
        super();
    }

    public Telefone(Long id, String ddd, String numero, EnumTipoTelefone tipo, Contato contato) {
        super();
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        this.contato = contato;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public EnumTipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoTelefone tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Telefone [");
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
        if (ddd != null) {
            builder.append("ddd=").append(ddd).append(", ");
        }
        if (numero != null) {
            builder.append("numero=").append(numero).append(", ");
        }
        if (tipo != null) {
            builder.append("tipo=").append(tipo).append(", ");
        }
        if (contato != null) {
            builder.append("contato=").append(contato);
        }
        builder.append("]");
        return builder.toString();
    }

}
