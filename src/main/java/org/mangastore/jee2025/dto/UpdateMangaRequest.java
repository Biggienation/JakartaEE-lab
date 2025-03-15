package org.mangastore.jee2025.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateMangaRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Pages is required")
    @Positive(message = "Pages must be a positive number")
    private int pages;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author must be less than 50 characters")
    private String author;

    @Size(max = 200, message = "Description must be less than 200 characters")
    private String description;

    @NotBlank(message = "Date is required")
    @Size(max = 12, message = "Date must be less than 12 characters")
    private String date;

    public UpdateMangaRequest() {}

    public UpdateMangaRequest(String title, int pages, String author,String description, String date) {
        this.title = title;
        this.pages = pages;
        this.author = author;
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

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {this.date = date;}
}
