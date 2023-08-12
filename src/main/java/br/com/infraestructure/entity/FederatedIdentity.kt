package br.com.infraestructure.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "federated_identity", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class FederatedIdentity(
    @Id @Column(name = "identity_provider") var identityProvider : String,
    @Column(name = "realm_id") var realmId : String,
    @Column(name = "federated_username") var federatedUsername : String,
    @Column(name = "federated_user_id") var federatedUserId : String,
    @Column(name = "token") var token : String,
    @JsonBackReference @ManyToOne @JoinColumn(name = "user_id") var user : UserEntity?
) {
    constructor() : this("", "", "","","", null)
}