package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implementacion de la clase Category.
 * @author Rodriguez Garcia Ulises.
 * @version 15.02.23.
 */
@Entity
@Table(name = "category")
public class Category {

    /* Id de la categoria. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;

    /* Nombre de la categoria. */
    @NotNull
    @Column(name  = "category")
    private String category;

    /* Acronimo de la categoria. */
    @NotNull
    @Column(name ="acronym")
    private String acronym;

    /* Estatus de la categoria. */
    @Column(name = "status")
    @Min(value = 0, message = "status must be 0 or 1")
    @Max(value = 1, message = "status must be 0 or 1")
    @JsonIgnore
    private Integer status;

    /**
     * Contructor de la clase Category.
     */
    public Category() {}
    
    /**
     * Obtiene el id de la categoria.
     * @return category_id.
     */
    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * Establece el id de la categoria.
     * @param category_id id de la categoria.
     */
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Obtiene el nombre de la categoria.
     * @return category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece el nombre de la categoria.
     * @param category_id nombre de la categoria.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene el acronimo de la categoria.
     * @return acronym.
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Establece el acronimo de la categoria.
     * @param category_id acronimo de la categoria.
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     * Obtiene el estatus de la categoria.
     * @return acronym.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el estatus de la categoria.
     * @param category_id estatus de la categoria.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Regresa una representacion de la Categoria.
     * @return represantacion en forma de cadena.
     */
    public String toString() {
        return "Category [category_id=" + category_id + ", category=" + category + ", acronym=" + acronym + ", status=" + status + "]";
    }
}