package me.dio.diogameawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.diogameawards.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
}
