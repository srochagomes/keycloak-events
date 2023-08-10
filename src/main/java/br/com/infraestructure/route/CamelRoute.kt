package br.com.infraestructure.route

import br.com.infraestructure.entity.UserEntityEvent
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.enterprise.context.ApplicationScoped
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jackson.JacksonDataFormat


@ApplicationScoped
class CamelRoute(val objectMapper: ObjectMapper): RouteBuilder() {

    override fun configure() {
        val jacksonDataFormat = JacksonDataFormat(objectMapper, Object::class.java)

        from("{{events.origin.user-entity}}")
            .routeId("USER_ENTITY_EVENTS")
            .routeDescription("User entity events")
            .marshal(jacksonDataFormat)
            .log("mensagem recebida de tabela: \${body}")
            .to("{{events.destiny.rabbit-mq.user.events}}")
            .end()
    }




}