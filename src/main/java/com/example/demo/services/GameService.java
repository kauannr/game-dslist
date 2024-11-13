package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.GameCompleteDTO;
import com.example.demo.dtos.GameMinDTO;
import com.example.demo.model.Game;
import com.example.demo.repositories.GameMinProjection;
import com.example.demo.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {

        List<Game> games = gameRepository.findAll();
        if (games.isEmpty()) {
            throw new EntityNotFoundException("Sem games cadastrados");
        }

        List<GameMinDTO> gamesMinDTO = games.stream().map(g -> new GameMinDTO(g)).toList();

        return gamesMinDTO;
    }

    @Transactional(readOnly = true)
    public GameCompleteDTO findById(long id) {

        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game não encontrado"));

        return new GameCompleteDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {

        List<GameMinProjection> games = gameRepository.searchByList(listId);
        if (games.isEmpty()) {
            throw new EntityNotFoundException("Sem games cadastrados");
        }

        List<GameMinDTO> gamesMinDTO = games.stream().map(g -> new GameMinDTO(g)).toList();

        return gamesMinDTO;
    }

}
