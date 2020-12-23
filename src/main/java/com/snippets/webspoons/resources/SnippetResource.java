package com.snippets.webspoons.resources;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snippets.webspoons.models.Snippet;
import com.snippets.webspoons.services.SnippetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/snippets")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SnippetResource {

	private final SnippetService snippetService;

	private final String resourcePath = "/snippets";

	// STORE SNIPPET
	@PostMapping
	public ResponseEntity<?> store(@Valid @RequestBody Snippet snippet) {
		try {
			snippet = snippetService.createSnippet(snippet, resourcePath);
			if(snippet.getId() == null) {
				return new ResponseEntity<>("Snippet already exist", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(snippet, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("ERROR OCCURRED>>>", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	// READ SNIPPET
	@GetMapping("/{uri}")
	public ResponseEntity<?> read(@NotBlank @PathVariable String uri) {
		try {
			Optional<Snippet> snippet = snippetService.findSnippet(uri);
			if (snippet.isPresent()) {
				Snippet gottenSnippet = snippet.get();
				gottenSnippet.setExpires_in(null);
				return new ResponseEntity<>(snippet.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("ERROR OCCURRED>>>", e);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	// UPDATE SNIPPET
	// READ SNIPPET
	@PutMapping("/like/{uri}")
	public ResponseEntity<?> likeSnippet(@NotBlank @PathVariable String uri) {
		try {
			Optional<Snippet> snippet = snippetService.likeSnippet(uri);
			if (snippet.isPresent()) {
				Snippet gottenSnippet = snippet.get();
				gottenSnippet.setExpires_in(null);
				return new ResponseEntity<>(snippet.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("ERROR OCCURRED>>>", e);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	// DELETE SNIPPET
}
