package com.training.finalEval.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

@Component
class jwtGenerator {
  /*  companion object{
        private val SECRET_KEY = "secret_key"

    }

   */
  val SECRET_KEY: String = "SECRET_KEY"
    fun createJWT (id: String, issuer:String, subjet: String, ttlMillis:Long, payload: String): String{

        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val  apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subjet)
            .setIssuer(issuer)
            .setAudience(payload)
            .signWith(signatureAlgorithm, signingKey)

        if(ttlMillis >= 0){
            val  expMills = nowMills + ttlMillis
            val exp = Date(expMills)
            builder.setExpiration(exp)
        }
        return builder.compact()
    }

    fun decodeJWT(jwt: String): Claims {
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).body
        return claims
    }


    }
