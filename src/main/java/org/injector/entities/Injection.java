package org.injector.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "INJECTIONS")
public class Injection {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String url;
    @Column
    private String ipAddress;

    public Injection(UUID id, String url) {
        this.id = id;
        this.url = url;
    }

    public Injection() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
