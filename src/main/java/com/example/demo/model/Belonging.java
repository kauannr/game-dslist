package com.example.demo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Belonging {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BelongingPK belongingPK = new BelongingPK();
    private Integer position;

    public Belonging(Game game, GameList gameList, Integer position) {
        this.belongingPK.setGame(game);
        this.belongingPK.setGameList(gameList);
        this.position = position;
    }

    public BelongingPK getBelongingPK() {
        return belongingPK;
    }

    public void setBelongingPK(BelongingPK belongingPK) {
        this.belongingPK = belongingPK;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
