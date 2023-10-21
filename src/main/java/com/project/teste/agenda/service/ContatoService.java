package com.project.teste.agenda.service;

import com.project.teste.agenda.model.Contato;
import com.project.teste.agenda.repository.ContatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Page<Contato> buscarTodosOsContatos(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }

    public Contato buscarPorId(Long id) {
        Optional<Contato> produto = contatoRepository.findContatoById(id);
        return produto.orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

    public List<Contato> buscarPorNome(String nome) {
        return contatoRepository.findByNomeContaining(nome);
    }

    public List<Contato> buscarPorTelefone(String telefone) {
        return contatoRepository.findByTelefone(telefone);
    }

    public Contato criarOuAtualizarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    public void deletarContato(Long id) {
        Optional<Contato> produto = contatoRepository.findContatoById(id);
        if (produto.isPresent()) {
            contatoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contato não encontrado para deletar");
        }
    }

    public Contato atualizarContato(Long id, Contato contatoAtualizado) {
        Optional<Contato> contatoExistente = contatoRepository.findContatoById(id);
        if (contatoExistente.isPresent()) {
            Contato contato = contatoExistente.get();
            contato.setNome(contatoAtualizado.getNome());
            contato.setEmail(contatoAtualizado.getEmail());
            contato.setTelefone(contatoAtualizado.getTelefone());
            contato.setDataNascimento(contatoAtualizado.getDataNascimento());
            contato.setUrlImagemPerfil(contatoAtualizado.getUrlImagemPerfil());
            return contatoRepository.save(contato);
        } else {
            throw new RuntimeException("Contato não encontrado para atualizar");
        }
    }
}