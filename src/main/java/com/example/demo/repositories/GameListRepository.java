package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {
    
}
