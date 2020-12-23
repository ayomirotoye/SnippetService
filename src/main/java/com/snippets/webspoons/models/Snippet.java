package com.snippets.webspoons.models;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "name", "expires_at", "snippet", "likes" })
public class Snippet {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public LocalDateTime getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(LocalDateTime expires_at) {
		this.expires_at = expires_at;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Snippet [ name=" + name + ", expires_in=" + expires_in + ", expires_at=" + expires_at
				+ ", snippet=" + snippet + ", url=" + url + ", likes=" + likes + "]";
	}

	public Snippet(@NotBlank @NotEmpty @Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "Only letters, numbers, dashes and hyphen is allowed for names") String name,
			@Min(value = 1, message = "The expiry duration must not be less that {value}") Integer expires_in,
			LocalDateTime expires_at, @NotBlank @NotEmpty String snippet, String url, Integer likes) {
		super();
		this.name = name;
		this.expires_in = expires_in;
		this.expires_at = expires_at;
		this.snippet = snippet;
		this.url = url;
		this.likes = likes;
	}

	public Snippet() {
		// TODO Auto-generated constructor stub
	}

}
