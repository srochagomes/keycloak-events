package br.com.infraestructure.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.quarkus.jackson.ObjectMapperCustomizer
import jakarta.inject.Singleton

@Singleton
class JsonObjectMapperConfig(): ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper?) {
        objectMapper?.registerModule(JavaTimeModule())

    }
}