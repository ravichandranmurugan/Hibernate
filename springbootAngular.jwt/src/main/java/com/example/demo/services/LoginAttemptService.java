package com.example.demo.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {

	private static final int MAXIMUM_NO_OF_ATTEMPTS = 5;
	private static final int ATTEMPTS_INCREMENT = 1;
	private LoadingCache<String, Integer> loginAttemptCache;

	public LoginAttemptService() {
		super();
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(100)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});

	}

	public void evictUserFormLoginAttemptCache(String username) {
		loginAttemptCache.invalidate(username);
	}

	public void addUserTOLoginAttemptCache(String username) throws ExecutionException {
		int attempts = 0;

		attempts = ATTEMPTS_INCREMENT + loginAttemptCache.get(username);
		loginAttemptCache.put(username, attempts);

	}

	public boolean hasExceedMaxLoginAttempts(String username) throws ExecutionException {
		return loginAttemptCache.get(username) >= MAXIMUM_NO_OF_ATTEMPTS;
	}
}
