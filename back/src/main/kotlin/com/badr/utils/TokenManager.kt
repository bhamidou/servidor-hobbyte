package com.badr.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.algorithms.Algorithm.HMAC256
import com.badr.models.UserModel.User
import java.util.Date


class TokenManager (){

    var secret = Params.secret
    var issuer = Params.issuer
    var audience = Params.audience

    fun generateJWTToken(user:User):String{
        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("id", user.userId)
          //  .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))

        return token
    }

    fun verifyJWTToken() : JWTVerifier {
        return JWT.require(HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}