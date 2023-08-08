package infraestructure.dto.entry

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
data class SpecificationDetail(var emailTo: String?,
                                var nameTo: String?,
                                var subject: String?,
                                var urlConfirmation: String?) {
    constructor() : this(null, null, null, null)
}
