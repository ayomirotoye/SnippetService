//package com.snippets.webspoons.services;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//import com.snippets.webspoons.models.Snippet;
//import com.snippets.webspoons.repositories.SnippetRepository;
//import com.snippets.webspoons.utilities.UrlUtils;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class SnippetService {
//	private final HttpServletRequest httpServletReq;
//	private final Environment env;
//	private final SnippetRepository snippetRepo;
//
//	public Snippet createSnippet(Snippet snippet, String resourcePath) {
//		Optional<Snippet> checkIfExists = snippetRepo.findByName(snippet.getName());
//		if (checkIfExists.isPresent()) {
//			return snippet;
//		}
//		LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(snippet.getExpires_in());
//		snippet.setExpires_at(expiresAt);
//		String url = UrlUtils.createURL(httpServletReq, resourcePath.concat("/").concat(snippet.getName()));
//		snippet.setUrl(url);
//		Snippet createdSnippet = snippetRepo.save(snippet);
//		createdSnippet.setExpires_in(null);
//		return createdSnippet;
//	}
//
//	public Optional<Snippet> findSnippet(String uri) {
//		Optional<Snippet> getSnippet = snippetRepo.findByName(uri);
//		if (getSnippet.isPresent()) {
//			Snippet theSnippet = getSnippet.get();
//			LocalDateTime now = LocalDateTime.now();
//			LocalDateTime theSnippetExpires = theSnippet.getExpires_at();
//
//			// IF NOW IS AFTER EXPIRY DATE RETURN NOT FOUND
//			if (now.isBefore(theSnippetExpires)) {
//				return getSnippet;
//			} else {
//				return Optional.empty();
//			}
//
//		} else {
//			return Optional.empty();
//		}
//	}
//
//	public Optional<Snippet> likeSnippet(String uri) {
//		Optional<Snippet> getSnippet = findSnippet(uri);
//		if (getSnippet.isPresent()) {
//			Snippet snippet = getSnippet.get();
//			Integer likedCnt = snippet.getLikes();
//			Snippet updatedSnippet = new Snippet();
//			if (likedCnt != null) {
//				snippet.setLikes(likedCnt + 1);
//			} else {
//				snippet.setLikes(1);
//			}
//			int expiryAdd = Integer.parseInt(env.getProperty("expiry.extension.duration.in_seconds", "0"));
//			LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiryAdd);
//			snippet.setExpires_at(expiresAt);
//			updatedSnippet = snippetRepo.save(snippet);
//			return Optional.of(updatedSnippet);
//		} else {
//			return Optional.empty();
//		}
//	}
//
//}
