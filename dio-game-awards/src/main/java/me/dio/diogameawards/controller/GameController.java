package me.dio.diogameawards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.dio.diogameawards.model.Game;
import me.dio.diogameawards.service.GameService;

@RestController
public class GameController extends BaseRestController {
    
    @Autowired
    private GameService gameService;

//Get Methods
    @GetMapping("games")
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findById(id));
    }


//Post Methods
    @PostMapping("games")
    public ResponseEntity<Game> insert(@RequestBody Game game) {
        gameService.insert(game);
        return new ResponseEntity<Game>(game, HttpStatus.CREATED);
    }


//Put Methods
    @PutMapping("games/{id}")
    public ResponseEntity<Game> updateGameData(@PathVariable long id, @RequestBody Game game) {
        gameService.updateGameData(id, game);
        return ResponseEntity.ok(game);
    }

    @PatchMapping("games/{id}/vote")
    public ResponseEntity<Game> vote(@PathVariable long id) {
        gameService.vote(id);
        return ResponseEntity.ok().build();
    }

    
//Delete Methods
    @DeleteMapping("games/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.ok().body("game deletado com sucesso");
    }
}
