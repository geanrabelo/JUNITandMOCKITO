package com.br.junitmockito.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tb_user")
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private String password;


}
