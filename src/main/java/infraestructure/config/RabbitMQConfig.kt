package infraestructure.config

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory


class RabbitMQConfig {
    @ApplicationScoped
    fun  connectionFactory(@ConfigProperty(name = "host_rabbit") host: String,
                           @ConfigProperty(name = "user_rabbit") username: String,
                           @ConfigProperty(name = "pass_rabbit") pwd: String,
                           @ConfigProperty(name = "port_rabbit") port: Int,
    ): ConnectionFactory {
        var connectionFactory: CachingConnectionFactory = CachingConnectionFactory(host, port)
        connectionFactory.username = username
        connectionFactory.setPassword(pwd)
        return connectionFactory
    }
}