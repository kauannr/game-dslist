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

    @Transactional
    public GameMinDTO save(Game game) {

        game = gameRepository.save(game);
        GameMinDTO gameMinDTO = new GameMinDTO(game);

        return gameMinDTO;
    }

    @Transactional
    public GameMinDTO update(long idOldGame, Game game) {
        Game oldGame = gameRepository.findById(idOldGame)
                .orElseThrow(() -> new EntityNotFoundException("Game não encontrado"));

        oldGame.setGenre(game.getGenre());
        oldGame.setImgUrl(game.getImgUrl());
        oldGame.setLongDescription(game.getLongDescription());
        oldGame.setPlatforms(game.getPlatforms());
        oldGame.setScore(game.getScore());
        oldGame.setShortDescription(game.getShortDescription());
        oldGame.setTitle(game.getTitle());
        oldGame.setYearGame(game.getYearGame());

        gameRepository.save(oldGame);

        return new GameMinDTO(oldGame);
    }

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
