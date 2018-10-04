package com.spyapp.server.core.beans.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "VICTIM")
public class Victim {

    @Id
    @GeneratedValue
    @Column(name = "idVictim")
    private int id;

    @Column(name = "nameVictim")
    private String name;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Column(name = "cordinatesx")
    private Double cordinatesx;

    @Column(name = "cordinatesy")
    private Double cordinatesy;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "victim")
    private Set<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "STALKER_idSTALKER", nullable = false)
    private Stalker stalker;


    public Victim() {

    }

    public Victim(String name, Stalker stalker, Date lastUpdate, double cordinatesx, double cordinatesy) {
        this.name = name;
        this.stalker = stalker;
        this.lastUpdate = lastUpdate;
        this.cordinatesx = cordinatesx;
        this.cordinatesy = cordinatesy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stalker getStalker() {
        return stalker;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getCordinatesx() {
        return cordinatesx;
    }

    public void setCordinatesx(Double cordinatesx) {
        this.cordinatesx = cordinatesx;
    }

    public Double getCordinatesy() {
        return cordinatesy;
    }

    public void setCordinatesy(Double cordinatesy) {
        this.cordinatesy = cordinatesy;
    }


    @Override
    public String toString() {
        return "Victim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", cordinatesx=" + cordinatesx +
                ", cordinatesy=" + cordinatesy +
                ", stalker=" + stalker +
                '}';
    }

    public void setStalker(Stalker stalker) {
        this.stalker = stalker;
    }
}
