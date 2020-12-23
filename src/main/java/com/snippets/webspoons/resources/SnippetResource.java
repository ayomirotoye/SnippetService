package com.snippets.webspoons.resources;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.snippets.webspoons.services.SnippetDao;

@RequestMapping("/snippets")
@RestController
public class SnippetResource {

	@Autowired
	private SnippetDao snippetDao;

	private final String resourcePath = "/snippets";

	// STORE SNIPPET
	@PostMapping
	public ResponseEntity<?> store(@Valid @RequestBody Snippet snippet) {
		try {
			snippet = snippetDao.createSnippet(snippet, resourcePath);
			if(snippet == null) {
				return new ResponseEntity<>("Snippet already exist", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(snippet, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	// READ SNIPPET
	@GetMapping("/{uri}")
	public ResponseEntity<?> read(@NotBlank @PathVariable String uri) {
		try {
			Optional<Snippet> snippet = snippetDao.findSnippet(uri);
			if (snippet.isPresent()) {
				Snippet gottenSnippet = snippet.get();
				gottenSnippet.setExpires_in(null);
				return new ResponseEntity<>(snippet.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	// UPDATE SNIPPET
	// READ SNIPPET
	@PutMapping("/like/{uri}")
	public ResponseEntity<?> likeSnippet(@NotBlank @PathVariable String uri) {
		try {
			Optional<Snippet> snippet = snippetDao.likeSnippet(uri);
			if (snippet.isPresent()) {
				Snippet gottenSnippet = snippet.get();
				gottenSnippet.setExpires_in(null);
				return new ResponseEntity<>(snippet.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	// DELETE SNIPPET
}
