package com.jbhunt.personnel.team.entity.projection;

import org.springframework.data.rest.core.config.Projection;

import com.jbhunt.personnel.team.Team;

@Projection(name = "teamprojection", types = { Team.class })
public interface TeamProjection {

	Integer getTeamID();

	String getTeamName();
}
