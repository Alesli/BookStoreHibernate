package com.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book", schema = "public")
public class Book implements Serializable {

    @Id
    @Column(name = "id")
    @Access(value = AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "author")
    private String author;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "cost", columnDefinition = "NUMERIC(8,2)")
    private Double cost;

    @Basic
    @Column(name = "count")
    private Integer count;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(
            fetch = FetchType.LAZY)
    @JoinTable(name = "shop_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"),
            foreignKey = @ForeignKey(name = "fk_book_to_shop"))
    private List<Shop> shops;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(
            fetch = FetchType.LAZY)
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_book_to_user"))
    private List<User> users;

}
