package com.ggoreb.practice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String content;
	Date createDate;
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	User user;
	
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	Question question;
}
