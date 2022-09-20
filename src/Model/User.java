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
public class User {

    private Integer id_user;
    private String nom;
    private String prenom;
    private String mail;
    private String passeword;
    private Integer num_tel;
    private String role;
    private String image;

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    
    
    
    
    
    public User() {
    }

    public User(Integer id_user, String nom, String prenom, String mail, String passeword, Integer num_tel, String role, String image) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.passeword = passeword;
        this.num_tel = num_tel;
        this.role = role;
        this.image = image;
    }

    public User(String nom, String prenom, String mail, String passeword, Integer num_tel, String role, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.passeword = passeword;
        this.num_tel = num_tel;
        this.role = role;
        this.image = image;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasseword() {
        return passeword;
    }

    public void setPasseword(String passeword) {
        this.passeword = passeword;
    }

    public Integer getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(Integer num_tel) {
        this.num_tel = num_tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", passeword=" + passeword + ", num_tel=" + num_tel + ", role=" + role + ", image=" + image + '}';
    }

    
    
}
