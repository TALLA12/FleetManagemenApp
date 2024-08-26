package com.ardotech.VoitureManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "coutcarburant")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coutcarburant {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Basic(optional = false)
    @Column(name = "quantite",nullable = false)
    private BigDecimal quantite;

    @Basic(optional = false)
    @Column(name = "prix",nullable = false)
    private BigDecimal prix;

    @Column(name = "total",nullable = false)
    private BigDecimal total;

    @JoinColumn(name = "vehicule_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private Vehicule vehiculeId;

    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User userId;

    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        if (quantite != null && prix != null) {
            total = quantite.multiply(prix);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Vehicule getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(Vehicule vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
