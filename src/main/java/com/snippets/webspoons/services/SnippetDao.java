package com.snippets.webspoons.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.snippets.webspoons.models.Snippet;
import com.snippets.webspoons.utilities.UrlUtils;

@Service
public class SnippetDao {
	private ConcurrentHashMap<String, Snippet> snippetMap;
	@Autowired
	private HttpServletRequest httpServletReq;
	@Autowired
	private Environment env;

	public SnippetDao() {
		this.snippetMap = new ConcurrentHashMap<String, Snippet>();
	}

	public Snippet createSnippet(Snippet snippet, String resourcePath) {
		LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(snippet.getExpires_in());
		snippet.setExpires_at(expiresAt);
		String url = UrlUtils.createURL(httpServletReq, resourcePath.concat("/").concat(snippet.getName()));
		snippet.setUrl(url);
		Optional<Snippet> checkIfPresent = findSnippet(snippet.getName());
		if (checkIfPresent.isPresent()) {
			Snippet prevValue = checkIfPresent.get();
			LocalDateTime prevExp = prevValue.getExpires_at();
			LocalDateTime now = LocalDateTime.now();
			boolean checker = prevExp.isBefore(now);
			if (checker) {
				snippetMap.replace(snippet.getName(), snippet);
			}else {
				return null;
			}
		}else {
			snippetMap.put(snippet.getName(), snippet);
		}
//		if (null != snippetMap.putIfAbsent(snippet.getName(), snippet)) {
//			return null;
//		}
		snippet.setExpires_in(null);
		return snippet;
	}

	public Optional<Snippet> findSnippet(String uri) {
		Snippet getSnippet = snippetMap.get(uri);
		System.out.println("the map:::" + getSnippet);
		if (getSnippet != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime theSnippetExpires = getSnippet.getExpires_at();

			// IF NOW IS AFTER EXPIRY DATE RETURN NOT FOUND
			if (now.isBefore(theSnippetExpires)) {
				return Optional.of(getSnippet);
			} else {
				return Optional.empty();
			}

		} else {
			return Optional.empty();
		}
	}

	public Optional<Snippet> likeSnippet(String uri) {
		Optional<Snippet> getSnippet = findSnippet(uri);
		if (getSnippet.isPresent()) {
			Snippet snippet = getSnippet.get();
			Integer likedCnt = snippet.getLikes();
			Snippet updatedSnippet = new Snippet();
			if (likedCnt != null) {
				snippet.setLikes(likedCnt + 1);
			} else {
				snippet.setLikes(1);
			}
			int expiryAdd = Integer.parseInt(env.getProperty("expiry.extension.duration.in_seconds", "0"));
			LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiryAdd);
			snippet.setExpires_at(expiresAt);
			updatedSnippet = snippetMap.replace(snippet.getName(), (snippet));
			return Optional.of(updatedSnippet);
		} else {
			return Optional.empty();
		}
	}
}
