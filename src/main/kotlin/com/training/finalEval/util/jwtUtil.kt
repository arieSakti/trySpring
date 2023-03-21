package com.training.finalEval.util


import com.training.finalEval.domain.entity.userEntity
import com.training.finalEval.error.UnauthorizedException
import io.jsonwebtoken.Jwts

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.stereotype.Component
import java.util.*


@Component
class jwtUtil {
  fun generateJwtToken(issuer: String, user: userEntity): String {
    return Jwts.builder()
      .setIssuer(issuer)
      .setSubject(user.id.toString())
      .setIssuedAt(Date())
      .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day
      .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "s3CreTK3y").compact()
  }

  fun validateToken(token: String) {
    val claims = Jwts.parser().setSigningKey("s3CreTK3y").parseClaimsJws(token).body

    val expiredDate = claims.expiration
    val expired = expiredDate.before(Date())


    if (expired) {
      throw UnauthorizedException()
    }
  }
}