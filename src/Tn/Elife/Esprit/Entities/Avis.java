/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.Entities;

import java.util.logging.Logger;

/**
 *
 * @author siccaPrint
 */
public class Avis {

     private int id_avis;
    private int id_client;
    private String contenu;
    private String nom_client;

    public Avis() {
    }

    public Avis(int id_avis, int id_client, String contenu, String nom_client) {
        this.id_avis = id_avis;
        this.id_client = id_client;
        this.contenu = contenu;
        this.nom_client = nom_client;
    }

    public int getId_avis() {
        return id_avis;
    }

    public int getId_client() {
        return id_client;
    }

    public String getContenu() {
        return contenu;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", id_client=" + id_client + ", contenu=" + contenu + ", nom_client=" + nom_client + '}';
    }
    
    
}
