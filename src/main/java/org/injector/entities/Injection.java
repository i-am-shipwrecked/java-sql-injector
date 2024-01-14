package org.injector.entities;

import jakarta.persistence.*;

import java.net.URI;
import java.util.UUID;
@Entity
@Table(name = "INJECTIONS")
public class Injection {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private URI url;
    @Column
    private String ipAddress;

    public Injection(UUID id, URI url) {
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

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
