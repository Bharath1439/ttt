package com.example.tic_tok_toe.models;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot extends Player implements Serializable {

    private BotDifficultyLevel botDifficultyLevel;
//    private BotPlayingStrategy botPlayingStrategy;



}
