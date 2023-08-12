package br.com.infraestructure.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.quarkus.jackson.ObjectMapperCustomizer
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.inject.Singleton

@Singleton
@RegisterForReflection(targets = [br.com.infraestructure.entity.UserEntity::class,
    br.com.infraestructure.entity.UserEntityEvent::class,
    br.com.infraestructure.entity.FederatedIdentity::class,
    java.util.UUID::class] )
class JsonObjectMapperConfig(): ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper?) {
        objectMapper?.registerModule(JavaTimeModule())

    }
}