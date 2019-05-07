package com.jbhunt.personnel.team;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

/**
 * Team Entity
 *
 */
@Data
@EqualsAndHashCode(callSuper = false,exclude = "team")
@ToString(exclude = "team")
@Entity
@Table(name = "TeamMemberTeamAssignment", schema = "TEAM")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,scope=TeamMemberTeamAssignment.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamMemberTeamAssignment extends AuditEntity implements Serializable{
    private static final long serialVersionUID = -2263137862717015167L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TeamMemberTeamAssignmentID", unique = true, nullable = false)
    private Integer teamMemberTeamAssignmentID;
    
    @JsonBackReference 
    @RestResource(exported=false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamID", nullable = false)
    @NotNull(message = "mandatory_field")
    private Team team;
    
    @Column(name = "TeamID",updatable=false, insertable=false)
    private Integer teamID;
    
    @Column(name = "TeamMemberPersonID", nullable = false)
    private String teamMemberPersonID;

    @Column(name = "ExpirationTimestamp", nullable = false)
    @NotNull(message = "mandatory_field")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter=AuditFieldTimeStampConverter.class)
    private LocalDateTime expirationTimestamp;
    
    @Column(name = "EffectiveTimestamp", nullable = false)
    @NotNull(message = "mandatory_field")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter=AuditFieldTimeStampConverter.class)
    private LocalDateTime effectiveTimestamp;

    
    
}
