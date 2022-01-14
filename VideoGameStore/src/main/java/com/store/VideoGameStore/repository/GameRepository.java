package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(String name);
    boolean existsByName(String name);
    void deleteAllByName(String name);




}
