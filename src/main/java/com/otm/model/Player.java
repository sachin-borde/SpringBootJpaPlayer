package com.otm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerId;

	@Column(length = 15, nullable = false)
	private String playerName;

	private Long playerBudget;

	private Date playerDob;
}
