package ru.crabs.security;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class CrabsRefreshTokenPersistence implements RefreshTokenPersistence {

    @Override
    @EventListener
    public void persistToken(RefreshTokenGeneratedEvent event) {
        Long id = ((CrabsUserDetails) event.getUserDetails()).getId();
        String username = event.getUserDetails().getUsername();
        String token = event.getRefreshToken();
    }

    @Override
    public Publisher<UserDetails> getUserDetails(String refreshToken) {
        return Flowable.just(new CrabsUserDetails(1L, "user", Collections.singletonList("USER")));
    }
}
