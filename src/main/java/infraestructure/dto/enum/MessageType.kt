package infraestructure.dto.enum

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
enum class MessageType {

    EMAIL,
    WHATSAP,
    NONE

}