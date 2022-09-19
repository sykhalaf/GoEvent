/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.Entities;

/**
 *
 * @author siccaPrint
 */
public class Utilisateurs {
    
     private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private String pwd;
    private int num_tel;
    private String role;
    private String image;

    public Utilisateurs() {
    }

    public Utilisateurs(int id_user) {
        this.id_user = id_user;
    }

    public Utilisateurs(int id_user, String nom, String prenom, String email, String pwd, int num_tel, String role, String image) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.num_tel = num_tel;
        this.role = role;
        this.image = image;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pwd=" + pwd + ", num_tel=" + num_tel + ", role=" + role + ", image=" + image + '}';
    }
    
    
    
    
}
