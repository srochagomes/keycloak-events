package br.com.infraestructure.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_USER_EVENT", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@RegisterForReflection
@NamedQuery(name = "GetAll", query = "select u from UserEntityEvent u")
data class UserEntityEvent(

    @Id @Temporal(TemporalType.TIMESTAMP) @Column(name = "DT_HR_CREATED")
    @JsonSerialize(`as` = Date::class)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSSSS", timezone = "America/Sao_Paulo") var id : LocalDateTime,
    @Column(name = "DS_EVENT") var event : String,
    @ManyToOne @JoinColumn(name = "ID_USER") var user : UserEntity?,
    @Column(name = "DS_OLD_VALUE_JSON") var oldState : String,
    @Column(name = "DS_NEW_VALUE_JSON") var newState : String

) {

    constructor() : this(LocalDateTime.now(), "", null, "","")
}