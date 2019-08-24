package com.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user",schema ="public")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @Access(value = AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "pass")
    private String pass;

    @Basic
    @Column(name = "cash", columnDefinition = "NUMERIC(8,2)")
    private Double cash;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            foreignKey = @ForeignKey(name = "fk_user_to_book"))
    private List<Book> books;

    public User(String name, String pass, Double cash, List<Book> books) {
        this.name = name;
        this.pass = pass;
        this.cash = cash;
        this.books = books;
    }

    public User() {
    }
}
