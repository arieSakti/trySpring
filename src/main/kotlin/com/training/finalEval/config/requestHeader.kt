package com.training.finalEval.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.training.finalEval.domain.dto.respon.globalResponse
import com.training.finalEval.repository.userRepository
import com.training.finalEval.service.userService
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class requestHeader (private val userRepository: userRepository ): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val apiKey: String? = request.getHeader("apiKey")
        val body: globalResponse<String?> = globalResponse("T", "invalid Header", "")
        if (apiKey == null || apiKey.isEmpty()) {
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "aplication/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }
        val foundApiKey = userRepository.findByApiKey(apiKey.toString())
        println(foundApiKey)
        if (!foundApiKey.isPresent) {
            val body: globalResponse<String?> = globalResponse("F", "UnAuthorized", null)
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }

        val xContentOption: String? = request.getHeader("X-Content-Type-Options")
        if (xContentOption == null || xContentOption == "" || xContentOption != "nosniff") {
            val body: globalResponse<String?> = globalResponse("F", "UnAuthorized", null)
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }

        val xProtection: String? = request.getHeader("X-XSS-Protection")
        if (xProtection == null || xProtection == "" || xProtection != "1; mode=block") {
            val body: globalResponse<String?> = globalResponse("F", "UnAuthorized", null)
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }

        val xStrictTransport: String? = request.getHeader("Strict-Transport-Security")
        if (xStrictTransport == null || xStrictTransport == "" || xStrictTransport != "max-age=31536000; includeSubDomains; preload") {
            val body: globalResponse<String?> = globalResponse("F", "UnAuthorized", null)
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }

        val xFrame: String? = request.getHeader("X-Frame-Options")
        if (xFrame == null || xFrame == "" || xFrame != "SAMEORIGIN") {
            val body: globalResponse<String?> = globalResponse("F", "UnAuthorized", null)
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(convertObjectToJson(body))
            return
        }
        filterChain.doFilter(request, response)
    }

    fun convertObjectToJson(response: globalResponse<String?>): String {
        return ObjectMapper().writeValueAsString(response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        println("PATH: $path")
        return path.contains("/v1/user/signin") || path.contains("/v1/user/validation")
    }

}






//
// fun shouldNotFilter(request: HttpServletRequest): Boolean {
//    val path = request.requestURI
//    println("PATH: $path")
//    return path.contains("/v1/api/auth/signin") || path.contains("/v1/api/auth/validation")
//}


