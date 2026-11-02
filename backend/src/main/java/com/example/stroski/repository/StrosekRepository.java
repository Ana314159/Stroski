package com.example.stroski.repository;

import com.example.stroski.model.Strosek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StrosekRepository extends JpaRepository<Strosek, Long> {



    List<Strosek> findByNazivContainingIgnoreCase(String naziv);

    
    List<Strosek> findByKategorijaId(Long kategorijaId);
}