package com.jbhunt.personnel.team.entity.projection;

import org.springframework.data.rest.core.config.Projection;

import com.jbhunt.personnel.team.Team;
import com.jbhunt.personnel.team.TeamMemberTeamAssignment;

@Projection(name = "teamassignmentprojection", types = { TeamMemberTeamAssignment.class })
public interface TeamMemberTeamAssignmentProjection {
	Team getTeam();
}
