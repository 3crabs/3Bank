package ru.crabs.security;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class CrabsAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            String username = authenticationRequest.getIdentity().toString();
            String password = authenticationRequest.getSecret().toString();
            try {
                if (username.equals("user") && password.equals("password")) {
                    emitter.onNext(new CrabsUserDetails(1L, username, Collections.singleton("USER")));
                    emitter.onComplete();
                } else {
                    emitter.onError(new AuthenticationException(new AuthenticationFailed()));
                }
            } catch (Exception e) {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
