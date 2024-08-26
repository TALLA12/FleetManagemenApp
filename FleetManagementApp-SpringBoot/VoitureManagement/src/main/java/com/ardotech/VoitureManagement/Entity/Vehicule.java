package com.ardotech.VoitureManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "vehicule")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicule {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "marque",nullable = false)
    private String marque;

    @Basic(optional = false)
    @Column(name = "modele",nullable = false)
    private String modele;

    @Basic(optional = false)
    @Column(name = "plaque_immatriculation" ,nullable = false)
    private String plaqueImmatriculation;

    @Column(name = "date_achat",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "etat",nullable = false)
    private String etat;

    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getPlaqueImmatriculation() {
        return plaqueImmatriculation;
    }

    public void setPlaqueImmatriculation(String plaqueImmatriculation) {
        this.plaqueImmatriculation = plaqueImmatriculation;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
