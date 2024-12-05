package com.northcoders.recordshopapi.repository;

import com.northcoders.recordshopapi.model.AlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository <AlbumModel, Long> {
}
