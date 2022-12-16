package com.otm.model.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

	private int playerId;

	@NotEmpty
	@Size(min = 2, message = "Player name Must Greater than 2 character")
	private String playerName;

	@NotNull
	private Long playerBudget;

	@NotNull
	@DateTimeFormat
	private Date playerDob;

}
