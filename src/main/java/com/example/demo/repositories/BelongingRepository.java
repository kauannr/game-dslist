package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Belonging;
import com.example.demo.model.BelongingPK;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {

    public Integer findMaxPositionByListId(long id);
    
}
