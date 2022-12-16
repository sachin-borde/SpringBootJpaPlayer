package com.otm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otm.model.dto.PlayerDto;
import com.otm.payloads.ApiResponse;
import com.otm.payloads.AppConstants;
import com.otm.payloads.PlayerResponse;
import com.otm.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@PostMapping("/savePlayerData")
	public ResponseEntity<PlayerDto> savePlayerData(@Valid @RequestBody PlayerDto playerDto) {
		PlayerDto savePlayerData = playerService.savePlayerData(playerDto);
		return new ResponseEntity<PlayerDto>(savePlayerData, HttpStatus.CREATED);
	}

	@GetMapping("/getPlayerDataById/{playerId}")
	public ResponseEntity<PlayerDto> getPlayerDataById(@PathVariable("playerId") Integer playerId) {
		PlayerDto getPlayerDataById = playerService.getPlayerDataById(playerId);
		return new ResponseEntity<PlayerDto>(getPlayerDataById, HttpStatus.OK);
	}

	@GetMapping("/getAllPlayerData/")
	public ResponseEntity<PlayerResponse> getAllPlayerData(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_PLAYER, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PlayerResponse getAllPlayerData = playerService.getAllPlayerData(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PlayerResponse>(getAllPlayerData, HttpStatus.OK);
	}

	@GetMapping("/getPlayerDataByName/{keyword}")
	public ResponseEntity<List<PlayerDto>> getPlayerDataByName(@PathVariable("keyword") String keyword) {

		List<PlayerDto> getPlayerDataByName = playerService.getPlayerDataByName(keyword);

		return new ResponseEntity<List<PlayerDto>>(getPlayerDataByName, HttpStatus.OK);
	}

	@PutMapping("/updatePlayerDataById/{playerId}")
	public ResponseEntity<PlayerDto> updatePlayerDataById(@Valid @RequestBody PlayerDto playerDto,
			@PathVariable("playerId") Integer playerId) {
		PlayerDto updatePlayerDataById = playerService.updatePlayerDataById(playerDto, playerId);
		return new ResponseEntity<PlayerDto>(updatePlayerDataById, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePlayerDataById/{playerId}")
	public ResponseEntity<ApiResponse> deletePlayerDataById(@PathVariable("playerId") Integer playerId) {
		playerService.deletePlayerDataById(playerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true),
				HttpStatus.OK);
	}
}
