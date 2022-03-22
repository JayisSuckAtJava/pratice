package com.ggoreb.practice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String subject;
	String content;

	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	User user;
	
	@OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
	List<Answer> answers;
	
}
