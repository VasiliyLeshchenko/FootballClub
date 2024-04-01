package com.footballclub.core.dto.mapper;

import com.footballclub.core.dto.GoalDTO;
import com.footballclub.core.entity.Game;
import com.footballclub.core.dto.PlayerStatisticsDTO;

public class GoalMapper {
    public static Game toGame(GoalDTO goal) {
        Game game = new Game();
        game.setId(goal.getGame().getId());

        if (goal.getHomeClub()) {
            game.setHomeClubScore(1);
            game.setAwayClubScore(0);
        } else {
            game.setHomeClubScore(0);
            game.setAwayClubScore(1);
        }

        return game;
    }

    public static PlayerStatisticsDTO toPlayerStatisticsDTO(GoalDTO goal) {
        return new PlayerStatisticsDTO(
                null,
                goal.getPlayer(),
                goal.getClub(),
                goal.getNumGoals()
        );
    }
}
