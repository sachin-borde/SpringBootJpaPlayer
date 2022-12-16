package com.otm.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.otm.exceptions.ResourceNotFoundException;
import com.otm.model.Player;
import com.otm.model.dto.PlayerDto;
import com.otm.payloads.PlayerResponse;
import com.otm.repository.PlayerRepository;
import com.otm.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PlayerDto savePlayerData(PlayerDto playerDto) {

		Player player = modelMapper.map(playerDto, Player.class);

		Player savePlayerData = playerRepository.save(player);

		return modelMapper.map(savePlayerData, PlayerDto.class);
	}

	@Override
	public PlayerDto getPlayerDataById(Integer playerId) {

		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("player", "Player Id", playerId));

		return modelMapper.map(player, PlayerDto.class);
	}

	@Override
	public PlayerResponse getAllPlayerData(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Player> pagePlayer = playerRepository.findAll(p);
		List<Player> players = pagePlayer.getContent();

		List<PlayerDto> playerDtos = players.stream().map((product) -> modelMapper.map(product, PlayerDto.class))
				.collect(Collectors.toList());

		PlayerResponse playerResponse = new PlayerResponse();

		playerResponse.setContent(playerDtos);
		playerResponse.setPageNumber(pagePlayer.getNumber());
		playerResponse.setPageSize(pagePlayer.getSize());
		playerResponse.setTotalElements(pagePlayer.getTotalElements());
		playerResponse.setTotalPages(pagePlayer.getTotalPages());
		playerResponse.setLastPage(pagePlayer.isLast());

		return playerResponse;
	}

	@Override
	public List<PlayerDto> getPlayerDataByName(String keyword) {

		List<Player> players = this.playerRepository.findByPlayertName("%" + keyword + "%");

		List<PlayerDto> playerDtos = players.stream().map((player) -> this.modelMapper.map(player, PlayerDto.class))
				.collect(Collectors.toList());

		return playerDtos;
	}

	@Override
	public PlayerDto updatePlayerDataById(PlayerDto playerDto, Integer playerId) {

		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player", "Player Id", playerId));

		player.setPlayerName(playerDto.getPlayerName());
		player.setPlayerBudget(playerDto.getPlayerBudget());
		player.setPlayerDob(playerDto.getPlayerDob());

		Player updatePlayerDataById = playerRepository.save(player);

		return modelMapper.map(updatePlayerDataById, PlayerDto.class);
	}

	@Override
	public void deletePlayerDataById(Integer playerId) {

		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player", "Player Id", playerId));

		playerRepository.delete(player);
	}
}
