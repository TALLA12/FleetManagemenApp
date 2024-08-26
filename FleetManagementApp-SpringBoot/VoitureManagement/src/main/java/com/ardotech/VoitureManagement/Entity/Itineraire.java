package com.ardotech.VoitureManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "itineraire")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Itineraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "origine",nullable = false)
    private String origine;

    @Basic(optional = false)
    @Column(name = "destination",nullable = false)
    private String destination;

    @Basic(optional = false)
    @Column(name = "distance",nullable = false)
    private BigDecimal distance;

    @Basic(optional = false)
    @Column(name = "duree",nullable = false)
    private BigDecimal duree;

    @Basic(optional = false)
    @Column(name = "date_depart",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDepart;

    @Basic(optional = false)
    @Column(name = "date_arrivee",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateArrivee;



    @JoinColumn(name = "conducteur_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private Conducteur conducteurId;

    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getDuree() {
        return duree;
    }

    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }



    public Conducteur getConducteurId() {
        return conducteurId;
    }

    public void setConducteurId(Conducteur conducteurId) {
        this.conducteurId = conducteurId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
