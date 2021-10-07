package com.exercise.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.interview.model.Player;
import com.exercise.interview.model.Tournaments;
import com.exercise.interview.service.TournamentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;
    
    @PostMapping(value = "/insert-tournament")
    public void insertTournament(@RequestBody Tournaments tournaments) throws Exception {
        tournamentService.insertTournament(tournaments);
    }
    
    @PostMapping(value = "/insert-player")
    public void insertPlayer(@RequestBody Player player) throws Exception {
        tournamentService.insertPlayer(player);
    }
    
    @PutMapping(value = "/update-tournament")
    public void updateTournament(@RequestBody Tournaments tournaments) throws Exception {
        tournamentService.updateTournament(tournaments);
    }
    
    @PutMapping(value = "/update-player")
    public void updatePlayer(@RequestBody Player player) throws Exception {
        tournamentService.updatePlayer(player);
    }
    
    @GetMapping(value = "/select-tournament")
    public List<Tournaments> selectAlltournaments()throws Exception{
        return tournamentService.selectAllTournaments();
    }
    
    @GetMapping(value = "/select-player")
    public List<Player> selectAllPlayer(@RequestParam Long tournamentId)throws Exception{
        return tournamentService.selectAllPlayers(tournamentId);
    }
    
    @DeleteMapping(value="/delete-tournament")
    public void deleteTournament(@RequestParam Long tournamentId)throws Exception{
        tournamentService.deleteTournamentPlayers(tournamentId);
    }
    
    @DeleteMapping(value="/delete-player")
    public void deletePlayer(@RequestParam Long playerId)throws Exception{
        tournamentService.deletePlayer(playerId);
    }
    
    
}
