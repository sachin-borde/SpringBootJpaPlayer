package com.otm.service;

import java.util.List;

import com.otm.model.dto.PlayerDto;
import com.otm.payloads.PlayerResponse;

public interface PlayerService {

    PlayerDto savePlayerData(PlayerDto playerDto);

    PlayerDto getPlayerDataById(Integer playerId);

    PlayerResponse getAllPlayerData(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<PlayerDto> getPlayerDataByName(String keyword);

    PlayerDto updatePlayerDataById(PlayerDto playerDto, Integer playerId);

    void deletePlayerDataById(Integer playerId);

}
