package com.snippets.webspoons.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "name", "expires_at", "snippet", "likes" })
public class Snippet {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "Only letters, numbers, dashes and hyphen is allowed for names")
	private String name;
	@Min(value = 1, message = "The expiry duration must not be less that {value}")
	private Integer expires_in;
	private LocalDateTime expires_at;
	@NotBlank
	@NotEmpty
	private String snippet;
	private String url;
	private Integer likes;
}
