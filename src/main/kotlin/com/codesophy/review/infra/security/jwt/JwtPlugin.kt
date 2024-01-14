package com.codesophy.review.infra.security.jwt

import com.codesophy.review.domain.exception.InvalidCredentialException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
        @Value("\${auth.jwt.issuer}") private val issuer: String,
        @Value("\${auth.jwt.secret}") private val secret: String,
        @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
        private val redisTemplate: RedisTemplate<String, String>
) {

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        if (redisTemplate.opsForValue().get(jwt) != null) {
            throw InvalidCredentialException()
        }

        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, email: String): String {
        return generateToken(subject, email, Duration.ofHours(accessTokenExpirationHour))
    }


    private fun generateToken(subject: String, email: String, expirationPeriod: Duration): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf("email" to email))
            .build()

        val now = Instant.now()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claims(claims)
                .signWith(key)
                .compact()
    }

}