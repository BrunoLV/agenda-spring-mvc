package br.com.valhala.agenda.modelo.enums;

public enum EnumTipoTelefone {

    RESIDENCIAL("Residencial"), COMERCIAL("Comercial"), CELULAR("Celular");

    private String descricao;

    EnumTipoTelefone(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
