package com.ardotech.VoitureManagement.Entity;

import com.ardotech.VoitureManagement.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Date;

@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "role" , nullable = false)
    private String role;



    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.INACTIVE;




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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }




}
