package br.com.infraestructure.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.TemporalField
import java.util.*

@Entity
@Table(name = "user_entity", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
data class UserEntity(
    @Id @Column(name = "id") var id : String,
    @Column(name = "email") var email : String,
    @Column(name = "email_constraint") var emailConstraint : String,
    @Column(name = "email_verified") var emailVerified : Boolean,
    @Column(name = "enabled") var enabled : Boolean,
    @Column(name = "federation_link") var federationLink : String,
    @Column(name = "first_name") var firstName : String,
    @Column(name = "last_name") var lastName : String,
    @Column(name = "realm_id") var realmId : String,
    @Column(name = "username") var username : String,
    @Column(name = "created_timestamp") var createdTimestamp : Long,
    @Column(name = "service_account_client_link") var serviceAccountClientLink : String,
    @Column(name = "not_before") var notBefore : Int,
    @JsonManagedReference @OneToMany(mappedBy = "user") val federateIdentities: List<FederatedIdentity>

) {
    constructor() : this("", "", "", false,false, "", "", "", "", "", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), "", 0, listOf())
}