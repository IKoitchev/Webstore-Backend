package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(String name);

    Game findByGenre(String genre);

}
