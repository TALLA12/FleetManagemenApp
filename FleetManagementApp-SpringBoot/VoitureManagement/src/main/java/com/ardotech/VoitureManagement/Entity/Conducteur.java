package com.ardotech.VoitureManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Table(name = "conducteur")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conducteur {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "nom",nullable = false)
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom",nullable = false)
    private String prenom;
    @Basic(optional = false)
    @Column(name = "date_naissance",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @Column(name = "numero_telephone",nullable = false)
    private String numeroTelephone;
    @Column(name = "adresse",nullable = false)
    private String adresse;
    @Column(name = "email",nullable = false)
    private String email;
    @Basic(optional = false)
    @Column(name = "numero_permis",nullable = false)
    private String numeroPermis;
    @Basic(optional = false)
    @Column(name = "date_delivrance_permis",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDelivrancePermis;
    @Basic(optional = false)
    @Column(name = "date_expiration_permis",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateExpirationPermis;
    @Basic(optional = false)
    @Column(name = "type_permis",nullable = false)
    private String typePermis;
    @Basic(optional = false)
    @Column(name = "date_debut_emploi",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebutEmploi;
    @Basic(optional = false)
    @Column(name = "statut_emploi",nullable = false)
    private String statutEmploi;
    @OneToMany(mappedBy = "conducteurId")
    private Collection<Itineraire> itineraireCollection = new ArrayList<>();
    @JoinColumn(name = "vehicule_assigne_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private Vehicule vehiculeAssigneId;
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User userId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(String numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public Date getDateDelivrancePermis() {
        return dateDelivrancePermis;
    }

    public void setDateDelivrancePermis(Date dateDelivrancePermis) {
        this.dateDelivrancePermis = dateDelivrancePermis;
    }

    public Date getDateExpirationPermis() {
        return dateExpirationPermis;
    }

    public void setDateExpirationPermis(Date dateExpirationPermis) {
        this.dateExpirationPermis = dateExpirationPermis;
    }

    public String getTypePermis() {
        return typePermis;
    }

    public void setTypePermis(String typePermis) {
        this.typePermis = typePermis;
    }

    public Date getDateDebutEmploi() {
        return dateDebutEmploi;
    }

    public void setDateDebutEmploi(Date dateDebutEmploi) {
        this.dateDebutEmploi = dateDebutEmploi;
    }

    public String getStatutEmploi() {
        return statutEmploi;
    }

    public void setStatutEmploi(String statutEmploi) {
        this.statutEmploi = statutEmploi;
    }

    public Collection<Itineraire> getItineraireCollection() {
        return itineraireCollection;
    }

    public void setItineraireCollection(Collection<Itineraire> itineraireCollection) {
        this.itineraireCollection = itineraireCollection;
    }

    public Vehicule getVehiculeAssigneId() {
        return vehiculeAssigneId;
    }

    public void setVehiculeAssigneId(Vehicule vehiculeAssigneId) {
        this.vehiculeAssigneId = vehiculeAssigneId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
