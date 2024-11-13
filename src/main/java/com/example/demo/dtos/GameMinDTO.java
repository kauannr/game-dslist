package com.example.demo.dtos;

import com.example.demo.model.Game;
import com.example.demo.repositories.GameMinProjection;

public class GameMinDTO {

    private String title;
    private Integer yearGame;
    private String imgUrl;
    private String shortDescription;

    public GameMinDTO() {
    }

    public GameMinDTO(Game game) {
        this.title = game.getTitle();
        this.yearGame = game.getYearGame();
        this.imgUrl = game.getImgUrl();
        this.shortDescription = game.getShortDescription();
    }

    public GameMinDTO(GameMinProjection gameMinProjection) {
        this.title = gameMinProjection.getTitle();
        this.yearGame = gameMinProjection.getYearGame();
        this.imgUrl = gameMinProjection.getImgUrl();
        this.shortDescription = gameMinProjection.getShortDescription();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getYearGame() {
        return yearGame;
    }

    public void setYearGame(Integer yearGame) {
        this.yearGame = yearGame;
    }

}
