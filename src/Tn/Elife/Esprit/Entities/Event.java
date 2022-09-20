/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.Entities;

import java.time.LocalDate;

/**
 *
 * @author siccaPrint
 */
public class Event {
    
private Integer id;
    private String nom;
    private String lieu;
    private String description;
    private float prix;
    private Long stockticket;
    private LocalDate date;
    private String type;
    private String id_categorie;

    public Event() {
    }

    public Event(Integer id, String nom, String lieu, String description, float prix, Long stockticket, LocalDate date, String type, String id_categorie) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.prix = prix;
        this.stockticket = stockticket;
        this.date = date;
        this.type = type;
        this.id_categorie = id_categorie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Long getStockticket() {
        return stockticket;
    }

    public void setStockticket(Long stockticket) {
        this.stockticket = stockticket;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }

   
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", prix=" + prix + ", stockticket=" + stockticket + ", date=" + date + ", type=" + type + ", id_categorie=" + id_categorie + '}';
    }

    
   
       
    
}

