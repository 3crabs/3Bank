package ru.crabs.security;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

import static java.util.Collections.singletonList;

@Singleton
public class CustomRefreshTokenHandler implements RefreshTokenPersistence {

    @Override
    @EventListener
    public void persistToken(RefreshTokenGeneratedEvent event) {
    }

    @Override
    public Publisher<UserDetails> getUserDetails(String refreshToken) {
        return Flowable.just(new UserDetails("user", singletonList("USER")));
    }
}
