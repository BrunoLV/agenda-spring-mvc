var moduloNovo = (function () {

    var telefones = [];
    var indiceTelefone = 0;

    resetaTelefone();

    function resetaTelefone() {
        $('#ddd').val(null);
        $('#telefone').val(null);
        $('#tipo').prop('selectedIndex', 0);
    }

    function adiciona() {
        var telefone = {};
        telefone.ddd = $('#ddd').val();
        telefone.numero = $('#telefone').val();
        telefone.tipo = $('#tipo').val();
        telefones[indiceTelefone++] = telefone;

        var divTelefones = $('#telefones');

        var tabela = null;
        if (!divTelefones.has('#tabelaTelefones').length) {
            var linhaCabecalho = document.createElement('tr');
            $(linhaCabecalho).append('<th scope="col">DDD</th>');
            $(linhaCabecalho).append('<th scope="col">N\u00FAmero</th>');
            $(linhaCabecalho).append('<th scope="col">Tipo</th>');
            $(linhaCabecalho).append('<th scope="col" colspan="2" style="width: 10%;"></th>');

            var elementoTHead = document.createElement('thead');
            var elementoTBody = document.createElement('tbody');
            $(elementoTHead).append(linhaCabecalho);

            var elementoTabela = document.createElement('table');
            $(elementoTabela).addClass('table table-bordered');
            $(elementoTabela).append('<caption>Lista de Telefones</caption>');
            $(elementoTabela).prop('id', 'tabelaTelefones');
            $(elementoTabela).append(elementoTHead);
            $(elementoTabela).append(elementoTBody);

            divTelefones.append(elementoTabela);
            tabela = $(elementoTabela);
        } else {
            tabela = $('#tabelaTelefones');
        }

        var linha = document.createElement('tr');
        $(linha).append('<td>' + telefone.ddd + '</td>');
        $(linha).append('<td>' + telefone.numero + '</td>');
        $(linha).append('<td>' + telefone.tipo + '</td>');
        $(linha).append('<td><button type="button" class="btn btn-light" onclick="moduloNovo.editaTelefone(this)"><i class="fas fa-edit"></i></button></td>');
        $(linha).append('<td><button type="button" class="btn btn-danger" onclick="moduloNovo.removeTelefone(this)"><i class="fas fa-trash"></i></button></td>');
        tabela.find('tbody').append(linha);
        resetaTelefone();
    }

    function edita(gatilho) {
        var linha = $(gatilho).parent().parent();
        var indice = linha.prop('rowIndex');
        var telefone = telefones[indice - 1];
        $('#ddd').val(telefone.ddd);
        $('#telefone').val(telefone.numero);
        $('#tipo').val(telefone.tipo);
        remove(gatilho);
    }

    function reinicia() {
        $('#nome').val('');
        resetaTelefone();
        telefones = [];
        indiceTelefone = 0;
    }

    function envia() {
        var tabelaTelefones = $('#tabelaTelefones');
        var contato = {};
        contato.nome = $('#nome').val();
        contato.telefones = telefones;
        $.ajax({
                url: "/agenda-spring-mvc/contato/",
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(contato),
                success: function (data, status, jqXHR) {
                    reinicia();
                    var dialog = $('.modal');
                    dialog.find('.modal-title').text('Mensagem Informativa');
                    dialog.find('.modal-body').empty();
                    dialog.find('.modal-body').append('<p>Contato gravado com sucesso!</p>');
                    dialog.modal('show');
                },
                error: function (jqXHR, status, error) {
                    var dialog = $('.modal');
                    dialog.find('.modal-title').text('Erro');
                    dialog.find('.modal-body').empty();
                    dialog.find('.modal-body').append('<p>Ocorreu erro.</p>');
                    dialog.modal('show')
                }
            }
        );
    }

    function remove(gatilho) {
        var linha = $(gatilho).parent().parent();
        var indice = linha.prop('rowIndex');
        telefones.splice(indice - 1, 1);
        linha.remove();
        indiceTelefone--;
        if (indiceTelefone == 0) {
            var divTabela = $('#telefones');
            divTabela.find('table:first').remove();
        }
    }

    return {
        adicionaTelefone: function () {
            adiciona();
        },
        editaTelefone: function (gatilho) {
            edita(gatilho);
        },
        enviaContato: function () {
            envia();
        },
        removeTelefone: function (gatilho) {
            remove(gatilho);
        }
    }

})();
