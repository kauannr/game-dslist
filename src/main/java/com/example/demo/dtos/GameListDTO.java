package com.example.demo.dtos;

import com.example.demo.model.GameList;

public class GameListDTO {

    private Long id;
    private String name;
    
    public GameListDTO(GameList gameList) {
        this.id = gameList.getId();
        this.name = gameList.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
