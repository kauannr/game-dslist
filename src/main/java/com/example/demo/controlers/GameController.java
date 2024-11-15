package com.example.demo.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.GameCompleteDTO;
import com.example.demo.dtos.GameMinDTO;
import com.example.demo.services.GameService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<GameMinDTO> listGames = gameService.findAll();
        return ResponseEntity.ok().body(listGames);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameCompleteDTO> findById(@PathVariable("id") long id) {

        GameCompleteDTO completeDTO = gameService.findById(id);

        return ResponseEntity.ok().body(completeDTO);
    }

}
