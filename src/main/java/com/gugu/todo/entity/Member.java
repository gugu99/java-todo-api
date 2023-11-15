package com.gugu.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Table(name="member")
@Getter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; //oAuth를 쓰려면 id 말고 username
    private String password;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
