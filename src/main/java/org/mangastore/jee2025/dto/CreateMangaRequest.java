package org.mangastore.jee2025.dto;

public class CreateMangaRequest {
    private String title;
    private int pages;
    private String author;
    private String ISBN;

    public CreateMangaRequest() {}

    public CreateMangaRequest(String title, int pages, String author, String ISBN) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }
}
