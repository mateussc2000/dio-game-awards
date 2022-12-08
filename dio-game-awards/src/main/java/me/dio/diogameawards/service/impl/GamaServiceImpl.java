package me.dio.diogameawards.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import me.dio.diogameawards.model.Game;
import me.dio.diogameawards.repository.GameRepository;
import me.dio.diogameawards.service.GameService;
import me.dio.diogameawards.service.exception.BusinessException;
import me.dio.diogameawards.service.exception.NoContentException;

@Service
public class GamaServiceImpl implements GameService {
    
    @Autowired
    private GameRepository repository;


    @Override
    public List<Game> findAll() { return repository.findAll(Sort.by(Direction.DESC, "votes")); }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = repository.findById(id);

        return game.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) { 
       if (Objects.nonNull(game.getGameId())) {

        throw new BusinessException("O ID é diferente de NULL na inclusão");
       }
        repository.save(game); 
    }

    @Override
    public void updateGameData(Long id, Game game) {
        Game gameDb = findById(id);
        
        if (gameDb.getGameId() == game.getGameId()) {
            repository.save(game);
        } else {
            throw new BusinessException("Os IDs para alteração são divergentes");
        }
    }

    @Override
    public void vote(Long id) {
        Game game = findById(id);
        
        game.setVotes(game.getVotes() + 1);
        
        repository.save(game);
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);

        repository.delete(gameDb);
    }
}
