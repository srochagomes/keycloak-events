package infraestructure.dto.entry

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import infraestructure.dto.enum.MessageType
import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
data class Message(var type : MessageType,
            var createAt: LocalDateTime?,
            var specification : Specification) {

    constructor() : this(MessageType.NONE, null, Specification())
}