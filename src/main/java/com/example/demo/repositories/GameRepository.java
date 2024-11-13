package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(nativeQuery = true, value = """
                SELECT game.id, game.title, game.year_game AS "yearGame", game.img_url AS "imgUrl",
                game.short_description AS "shortDescription", belonging.position
                FROM game
                INNER JOIN belonging ON game.id = belonging.game_id
                WHERE belonging.game_list_id = :listId
                ORDER BY belonging.position
            """)
    List<GameMinProjection> searchByList(Long listId);
}
