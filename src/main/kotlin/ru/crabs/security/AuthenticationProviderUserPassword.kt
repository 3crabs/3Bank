package ru.crabs.security

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Singleton


// TODO: 03.10.2020 переписать на базу
@Singleton
class AuthenticationProviderUserPassword : AuthenticationProvider {
    override fun authenticate(httpRequest: HttpRequest<*>?, authenticationRequest: AuthenticationRequest<*, *>): Publisher<AuthenticationResponse> {
        return Maybe.create { emitter: MaybeEmitter<AuthenticationResponse> ->
            if (authenticationRequest.identity == "user" && authenticationRequest.secret == "password") {
                emitter.onSuccess(UserDetails("user", ArrayList()))
            } else {
                emitter.onError(AuthenticationException(AuthenticationFailed()))
            }
        }.toFlowable()
    }
}
