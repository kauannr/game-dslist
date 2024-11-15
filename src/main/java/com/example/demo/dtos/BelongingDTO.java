package com.example.demo.dtos;

import com.example.demo.model.Belonging;
import com.example.demo.model.Game;
import com.example.demo.model.GameList;

public class BelongingDTO {

    private Game game;
    private GameList gameList;
    private Integer position;

    public BelongingDTO(Belonging belonging) {

        this.game = belonging.getBelongingPK().getGame();
        this.gameList = belonging.getBelongingPK().getGameList();
        this.position = belonging.getPosition();

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
