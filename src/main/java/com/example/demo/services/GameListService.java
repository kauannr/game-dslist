package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.GameListDTO;
import com.example.demo.model.GameList;
import com.example.demo.repositories.GameListRepository;
import com.example.demo.repositories.GameMinProjection;
import com.example.demo.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional
    public GameListDTO save(GameList gameList) {
        gameListRepository.save(gameList);
        GameListDTO gameListDTO = new GameListDTO(gameList);

        return gameListDTO;
    }

    @Transactional
    public GameListDTO update(long idOldList, GameList newGameList) {

        GameList oldGameList = gameListRepository.findById(idOldList)
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada"));

        oldGameList.setName(newGameList.getName());

        gameListRepository.save(oldGameList);

        return new GameListDTO(oldGameList);

    }

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

    @Transactional
    public void move(long idlist, int sourceIndex, int targetIndex) {

        List<GameMinProjection> gamesFromList = gameRepository.searchByList(idlist);

        GameMinProjection game = gamesFromList.remove(sourceIndex);
        gamesFromList.add(targetIndex, game);

        int positionMin = sourceIndex < targetIndex ? sourceIndex : targetIndex;
        int positionMax = sourceIndex > targetIndex ? sourceIndex : targetIndex;

        for (int i = positionMin; i <= positionMax; i++) {
            gameListRepository.updateBelongingPosition(idlist, gamesFromList.get(i).getId(), i);
        }

    }

}