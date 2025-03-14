package org.mangastore.jee2025.dto;

public class ReturningMangaRequest {
    private String title;
    private int pages;
    private String author;
    private String ISBN;

    public ReturningMangaRequest() {}

    public ReturningMangaRequest(String title, int pages, String author, String ISBN) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.ISBN = ISBN;
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


}
