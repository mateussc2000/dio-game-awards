package me.dio.diogameawards.service;

import java.util.List;

import me.dio.diogameawards.model.Game;

public interface GameService {

    List<Game> findAll();

    Game findById(Long id);

    void insert(Game game);

    void updateGameData(Long id, Game game);
    
    void vote(Long id);
    
    void delete(Long id);
}
