package com.soulcode.projetofinal.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soulcode.projetofinal.models.Financeiro;

@Repository
public interface FiltroRepository extends JpaRepository <Financeiro, Integer>{

} 