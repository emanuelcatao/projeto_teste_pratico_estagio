//package com.project.teste.agenda.servicetest;
//
//import com.project.teste.agenda.model.Contato;
//import com.project.teste.agenda.repository.ContatoRepository;
//import com.project.teste.agenda.service.ContatoService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(MockitoExtension.class)
//public class ContatoServiceTest {
//
//    @Mock
//    private ContatoRepository contatoRepository;
//    @InjectMocks
//    private ContatoService contatoService;
//
//    List<Contato> contatos = Arrays.asList(
//            new Contato(1L, "Teste", "teste@email.com", LocalDate.now(), "(12)12345-1678"),
//            new Contato(2L, "Teste2", "teste@email.com", LocalDate.now(), "(12) 2345-1678"),
//            new Contato(3L, "Teste3", "teste@email.com", LocalDate.now(), "12123451678"),
//            new Contato(4L, "Teste4", "teste@email.com", LocalDate.now(), "(12)2345-1678"),
//            new Contato(5L, "Teste5", "teste@email.com", LocalDate.now(), "(12)12345-1678")
//    );
//
//    @Test
//    public void testBuscarPorIdComIdExistente() {
//        Contato contato = new Contato(1L, "Teste", "teste@email.com", LocalDate.now(), "(12)12345-1678");
//        Mockito.when(contatoRepository.findContatoById(1L)).thenReturn(Optional.of(contato));
//
//        Contato resultado = contatoService.buscarPorId(1L);
//
//        assertEquals(contato, resultado);
//    }
//
//    @Test
//    public void testBuscarPorIdComIdInexistente() {
//        Mockito.when(contatoRepository.findContatoById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> {
//            contatoService.buscarPorId(1L);
//        });
//    }
//}