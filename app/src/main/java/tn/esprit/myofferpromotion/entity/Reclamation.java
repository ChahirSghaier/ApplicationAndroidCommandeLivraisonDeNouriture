package tn.esprit.myofferpromotion.entity;

import java.io.Serializable;

public class Reclamation implements Serializable {

    private Long id;
    private String title;
    private String description;

    public Reclamation(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
