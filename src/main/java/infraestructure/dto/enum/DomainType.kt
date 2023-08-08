package infraestructure.dto.enum

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
enum class DomainType(val description:String) {
    ACCOUNT_CONFIRMATION("Confirmação da conta"),
    NONE("")
}