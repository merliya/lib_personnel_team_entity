package com.jbhunt.personnel.team;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jbhunt.infrastructure.audit.converter.AuditFieldTimeStampConverter;
import com.jbhunt.infrastructure.audit.entity.AuditEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,exclude = "teamMemberTeamAssignments")
@ToString(exclude = "teamMemberTeamAssignments")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,scope=Team.class)
@Table(name = "Team", schema = "TEAM")
public class Team extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -2263137862717015167L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TeamID", unique = true, nullable = false)
	private Integer teamID;

	@Column(name = "TeamName", unique = true, nullable = false)
	private String teamName;

	@Column(name = "TeamLeaderPersonID", nullable = false)
	private String teamLeaderPersonID;


	@Column(name = "createUserId", nullable = false)
	private String createUserId;

	@Column(name = "createTimestamp", nullable = false)
	private LocalDateTime createTimestamp;

	@Column(name = "lastUpdateUserId", nullable = false)
	private String lastUpdateUserId;

	@Column(name = "lastUpdateTimestamp", nullable = false)
	private LocalDateTime lastUpdateTimestamp;

	@Column(name = "EffectiveTimestamp", nullable = false)
	@NotNull(message = "mandatory_field")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Convert(converter=AuditFieldTimeStampConverter.class)
	private LocalDateTime effectiveTimestamp;

	@Column(name = "ExpirationTimestamp", nullable = false)
	@NotNull(message = "mandatory_field")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Convert(converter=AuditFieldTimeStampConverter.class)
	private LocalDateTime expirationTimestamp;
	
	@OneToMany(mappedBy="team",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonManagedReference
	private List<TeamMemberTeamAssignment> teamMemberTeamAssignments;
	
}
