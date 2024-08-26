package com.ardotech.VoitureManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "maintenance")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Maintenance {
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

    @Lob
    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "cout",nullable = false)
    private BigDecimal cout;

    @JoinColumn(name = "vehicule_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private Vehicule vehiculeId;

    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User userId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
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
