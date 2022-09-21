/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Marwen
 */
public class servicesModel {
    private int id ;
    private String type ; 
    private String description ; 

    public servicesModel() {
    }

    public servicesModel(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public servicesModel(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "servicesModel{" + "id=" + id + ", type=" + type + ", description=" + description + '}';
    }
    
}
