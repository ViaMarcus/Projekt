package com.exercise.interview.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.exercise.interview.model.Player;
import com.exercise.interview.model.Tournaments;

@Service
public class TournamentService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    private final String SELECT_TOURNAMENT = "SELECT * FROM tournaments";
    private final String INSERT_TOURNAMENT = "INSERT INTO tournaments(reward) values(?)";
    private final String UPDATE_TOURNAMENT = "UPDATE tournaments set reward=? where id=?";
    

    private final String SELECT_PLAYER = "SELECT * FROM player where tournamentId=?";
    private final String INSERT_PLAYER = "INSERT INTO player(tournamentId,player) values(?,?)";
    private final String UPDATE_PLAYER = "UPDATE player set player=?,tournamentId=? where id=?";

    private final String DELETE_TOURNAMENT = "DELETE FROM tournaments WHERE id=?";
    private final String DELETE_PLAYERS = "DELETE FROM player WHERE tournamentId=?";
    private final String DELETE_PLAYER = "DELETE FROM player WHERE id=?";
    
    public void insertTournament(Tournaments tournament) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_TOURNAMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, tournament.getReward());
                return ps;
            }
        }, holder);
    }
    
    public void updateTournament(Tournaments tournament) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_TOURNAMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, tournament.getReward());
                ps.setLong(2, tournament.getId());
                return ps;
            }
        }, holder);
    }
    
    public List<Tournaments> selectAllTournaments(){
        List<Tournaments> tournaments = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_TOURNAMENT);
        
        for (Map<String, Object> row : rows) 
        {
            Tournaments tournament = new Tournaments();
            tournament.setId((Long)row.get("id"));
            tournament.setReward((Double)row.get("reward"));
            tournaments.add(tournament);
         }
        return tournaments;
    }

    
    public void insertPlayer(Player player) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_PLAYER, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, player.getTournamentId());
                ps.setString(2, player.getPlayer());
                return ps;
            }
        }, holder);
    }
    
    public void updatePlayer(Player player) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_PLAYER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, player.getPlayer());
                ps.setLong(2, player.getTournamentId());
                ps.setLong(3, player.getId());
                return ps;
            }
        }, holder);
    }
    
    public List<Player> selectAllPlayers(Long tournamentId){
        List<Player> players = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_PLAYER,new Object[]{tournamentId});
        
        for (Map<String, Object> row : rows) 
        {
            Player player = new Player();
            player.setId((Long)row.get("id"));
            player.setPlayer((String)row.get("player"));
            player.setTournamentId((Long)row.get("tournamentId"));
            
            players.add(player);
         }
        return players;
    }
    
    public void deleteTournamentPlayers(Long tournamentId) {
        Object[] args = new Object[] {tournamentId};
        jdbcTemplate.update(DELETE_TOURNAMENT, args);
        jdbcTemplate.update(DELETE_PLAYERS, args);
    }
    
    public void deletePlayer(Long id) {
        Object[] args = new Object[] {id};
        jdbcTemplate.update(DELETE_PLAYER, args);
    }

}
