/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ce Pc
 */
public class Event {

    private Integer id_event;
    private String nom_event;
    private String lieux_event;
    private String description;
    private float prix;
    private Integer stock_ticket;
    private String date;
    private String type_event;
    private Integer id_categorie;

    public Event() {
    }

    public Event(String nom_event, String lieux_event, String description, float prix, Integer stock_ticket, String date, String type_event, Integer id_categorie) {
        this.nom_event = nom_event;
        this.lieux_event = lieux_event;
        this.description = description;
        this.prix = prix;
        this.stock_ticket = stock_ticket;
        this.date = date;
        this.type_event = type_event;
        this.id_categorie = id_categorie;
    }

    public Event(Integer id_event, String nom_event, String lieux_event, String description, float prix, Integer stock_ticket, String date, String type_event, Integer id_categorie) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.lieux_event = lieux_event;
        this.description = description;
        this.prix = prix;
        this.stock_ticket = stock_ticket;
        this.date = date;
        this.type_event = type_event;
        this.id_categorie = id_categorie;
    }

    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getLieux_event() {
        return lieux_event;
    }

    public void setLieux_event(String lieux_event) {
        this.lieux_event = lieux_event;
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

    public Integer getStock_ticket() {
        return stock_ticket;
    }

    public void setStock_ticket(Integer stock_ticket) {
        this.stock_ticket = stock_ticket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Event{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", lieux_event=" + lieux_event + ", description=" + description + ", prix=" + prix + ", stock_ticket=" + stock_ticket + ", date=" + date + ", type_event=" + type_event + ", id_categorie=" + id_categorie + '}';
    }
    
    
    
    
    
    
}
