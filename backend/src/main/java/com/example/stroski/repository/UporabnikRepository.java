package com.example.stroski.repository;

import com.example.stroski.model.Uporabnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {

    boolean existsByUporabniskoImeIgnoreCase(String uporabniskoIme);

    boolean existsByEmailIgnoreCase(String email);
}
