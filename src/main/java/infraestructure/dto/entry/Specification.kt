package infraestructure.dto.entry

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import infraestructure.dto.enum.DomainType
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
data class Specification(var domainType: DomainType,
                         var accountId: UUID?,
                         var detail: SpecificationDetail
) {
    constructor() : this(DomainType.NONE, null, SpecificationDetail())
}
