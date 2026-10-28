package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.Foto;


public interface FotoRepository extends JpaRepository<Foto, Long>  {
   List<Foto> findByUrl(String urlString);
}
