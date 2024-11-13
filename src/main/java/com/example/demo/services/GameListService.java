package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.GameListDTO;
import com.example.demo.model.GameList;
import com.example.demo.repositories.GameListRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {

        List<GameList> gameList = gameListRepository.findAll();
        if (gameList.isEmpty()) {
            throw new EntityNotFoundException("Sem listas cadastradas");
        }
        List<GameListDTO> gameListDTO = gameList.stream().map(x -> new GameListDTO(x)).toList();

        return gameListDTO;

    }

    @Transactional(readOnly = true)
    public GameListDTO findById(long id) {

        GameList gameList = gameListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada"));

        return new GameListDTO(gameList);
    }

}
