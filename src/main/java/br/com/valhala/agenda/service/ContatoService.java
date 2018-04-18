package br.com.valhala.agenda.service;

import br.com.valhala.agenda.jpa.ContatoRepository;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.modelo.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato buscaPorId(Long id) {
        Contato contato = repository.findById(id);
        return contato;
    }

    public List<Contato> lista() {
        List<Contato> lista = repository.findAll();
        return lista;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Contato salva(Contato contato) {
        List<Telefone> telefones = contato.getTelefones();
        if (telefones != null && !telefones.isEmpty()) {
            for (Telefone telefone :
                    telefones) {
                telefone.setContato(contato);
            }
        }
        repository.save(contato);
        return contato;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleta(Long id) {
        repository.delete(id);
    }

    public Boolean existe(Long id) {
        return repository.exists(id);
    }
}
