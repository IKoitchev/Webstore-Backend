package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAuthor(String Author);
    List<Review> findAllByGameId(long gameId);
    Optional<Review> findByAuthorAndGameId(String author, long gameId);
}
