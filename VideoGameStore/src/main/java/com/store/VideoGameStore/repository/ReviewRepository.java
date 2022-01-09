package com.store.VideoGameStore.repository;

import com.store.VideoGameStore.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
