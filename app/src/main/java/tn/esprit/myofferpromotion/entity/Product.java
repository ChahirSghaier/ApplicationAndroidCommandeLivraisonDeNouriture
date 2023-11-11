package tn.esprit.myofferpromotion.entity;

public class Product {
    private Long id;
    private String nom;
    private Long prix;
    private String description;

    public Product() {
    }

    public Product(Long id, String nom, Long prix, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public Long getId() {
        return id;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
