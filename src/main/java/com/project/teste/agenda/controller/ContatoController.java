package com.project.teste.agenda.controller;

import com.project.teste.agenda.dto.ImgurResponse;
import com.project.teste.agenda.model.Contato;
import com.project.teste.agenda.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/contatos")
@CrossOrigin(origins = "http://localhost:4200/")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Contato> buscarTodosOsContatos(Pageable pageable) {
        try {
            return contatoService.buscarTodosOsContatos(pageable);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar todos os contatos");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato buscarContatoPorId(@PathVariable("id") Long id) {
        try {
            return contatoService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato com ID " + id + " não encontrado");
        }
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> buscarContatosPorNome(@PathVariable("nome") String nome) {
        try {
            return contatoService.buscarPorNome(nome);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar contatos pelo nome " + nome);
        }
    }

    @GetMapping("/telefone/{telefone}")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> buscarContatosPorTelefone(@PathVariable("telefone") String telefone) {
        try {
            return contatoService.buscarPorTelefone(telefone);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar contatos pelo telefone " + telefone);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato criarContato(@Valid @RequestBody Contato contato) {
        try {
            return contatoService.criarOuAtualizarContato(contato);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar contato");
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizarContato(@PathVariable("id") Long id, @Valid @RequestBody Contato contatoAtualizado) {
        try {
            return contatoService.atualizarContato(id, contatoAtualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar contato com ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarContato(@PathVariable("id") Long id) {
        try {
            contatoService.deletarContato(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar contato com ID " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadToImgur(@RequestParam("file") MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String CLIENT_ID = "9e2de936d89e6ce";
            headers.set("Authorization", "Client-ID " + CLIENT_ID);

            byte[] bytes = file.getBytes();
            HttpEntity<byte[]> request = new HttpEntity<>(bytes, headers);

            RestTemplate restTemplate = new RestTemplate();
            String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/image";
            ResponseEntity<ImgurResponse> response = restTemplate.exchange(IMGUR_UPLOAD_URL, HttpMethod.POST, request, ImgurResponse.class);

            String imageUrl = Objects.requireNonNull(response.getBody()).getData().getLink();

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("imageUrl", imageUrl);

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao fazer upload da imagem: " + e.getMessage());
        }
    }


}

