package com.soulcode.projetofinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soulcode.projetofinal.models.Financeiro;

@Repository
public interface FinanceiroRepository extends JpaRepository <Financeiro, Integer>{
    
}
    // Um repository simplifica a interação com o banco de dados. Disponibilizando
    // uma interface que possui métodos básicos como listar, salvar e deletar.
    // Operações save, find by id, find all, delete