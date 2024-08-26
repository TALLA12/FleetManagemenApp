package com.ardotech.VoitureManagement.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String motDePasse;

    public LoginRequest(String username, String motDePasse) {
        this.username = username;
        this.motDePasse = motDePasse;
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
}
