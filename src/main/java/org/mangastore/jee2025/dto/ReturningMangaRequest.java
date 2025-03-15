package org.mangastore.jee2025.dto;

public class ReturningMangaRequest {
    private String title;
    private int pages;
    private String author;
    private String ISBN;
    private String description;
    private String date;

    public ReturningMangaRequest() {}

    public ReturningMangaRequest(String title, int pages, String author, String ISBN, String description, String date) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.ISBN = ISBN;
        this.description = description;
        this.date = date;
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
}
