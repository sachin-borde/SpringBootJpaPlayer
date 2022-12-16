package com.otm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otm.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	@Query("select p from Player p where p.playerName like :key")
	List<Player> findByPlayertName(@Param("key") String playerName);

}
