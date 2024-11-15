package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.BelongingDTO;
import com.example.demo.model.Belonging;
import com.example.demo.model.Game;
import com.example.demo.model.GameList;
import com.example.demo.repositories.BelongingRepository;
import com.example.demo.repositories.GameListRepository;
import com.example.demo.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BelongingService {

    @Autowired
    private BelongingRepository belongingRepository;

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public BelongingDTO addToTable(long idList, long idGame) {
        GameList gameList = gameListRepository.findById(idList)
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada"));

        Game game = gameRepository.findById(idGame)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));

        Belonging belonging = new Belonging(game, gameList, calculateMaxPosition(idList) + 1);

        belongingRepository.save(belonging);
        return new BelongingDTO(belonging);
    }

    private Integer calculateMaxPosition(long lisId) {
        Integer maxPosition = belongingRepository.findMaxPositionByListId(lisId);
        return maxPosition == null ? 0 : maxPosition;
    }

}
