package org.mangastore.jee2025.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "manga")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "isbn", unique = true, nullable = false)
    private String ISBN;

    public Manga() {}

    public Manga(String title, int pages, String author, String ISBN, String description, String date) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.ISBN = ISBN;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return getId() != null && Objects.equals(getId(), manga.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}