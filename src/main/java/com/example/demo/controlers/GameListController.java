package com.example.demo.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.BelongingDTO;
import com.example.demo.dtos.GameListDTO;
import com.example.demo.dtos.GameMinDTO;
import com.example.demo.dtos.ReplacementDTO;
import com.example.demo.services.BelongingService;
import com.example.demo.services.GameListService;
import com.example.demo.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @Autowired
    private BelongingService belongingService;

    @GetMapping()
    public ResponseEntity<List<GameListDTO>> findAllList() {

        List<GameListDTO> gameListDTO = gameListService.findAll();

        return ResponseEntity.ok().body(gameListDTO);
    }

    @GetMapping(value = "/{idList}/games")
    public ResponseEntity<?> findByList(@PathVariable("idList") long idList) {
        List<GameMinDTO> listGames = gameService.findByList(idList);
        return ResponseEntity.ok().body(listGames);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameListDTO> movePosition(@PathVariable("id") long id) {

        GameListDTO gameListDTO = gameListService.findById(id);

        return ResponseEntity.ok().body(gameListDTO);
    }

    @PostMapping(value = "{idList}/replacement")
    public ResponseEntity<?> replacementPosition(@PathVariable("idList") long idList,
            @RequestBody ReplacementDTO replacementDTO) {

        gameListService.move(idList, replacementDTO.getSourceIndex(), replacementDTO.getTargetIndex());

        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/{idList}/game/{idGame}")
    public ResponseEntity<?> addToTableBelonging(@PathVariable("idList") long idList, @PathVariable("idGame") long idGame) {

        BelongingDTO BelongingDTO = belongingService.addToTable(idList, idGame);

        return ResponseEntity.ok().body(
                "Jogo " + idGame + " adicionado na lista " + idList + " na posicao " + BelongingDTO.getPosition());
    }

}
