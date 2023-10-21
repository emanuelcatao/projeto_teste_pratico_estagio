package com.project.teste.agenda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "O nome é obrigatório.")
    @Size(min = 1, message = "O nome deve ter pelo menos 1 caractere.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @NotNull(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$", message = "Formato de telefone inválido. O formato esperado é (XX)XXXX-XXXX ou (XX)XXXXX-XXXX.")
    private String telefone;

    @Column(name = "url_imagem_perfil")
    private String urlImagemPerfil;
}
