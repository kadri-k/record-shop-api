package com.northcoders.recordshopapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // REPLACING getters,setters
@AllArgsConstructor
@Builder
@Table(name="albums")
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private int stock;

    @Column (nullable = false)
    private double price;

    public AlbumModel() {}

    public AlbumModel(String title, String artist, Genre genre, int stock, double price) {
           this.title = title;
           this.artist = artist;
           this.genre = genre;
           this.stock = stock;
           this.price = price;
    }
}




