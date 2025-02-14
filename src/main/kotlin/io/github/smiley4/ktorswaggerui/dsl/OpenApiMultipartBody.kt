package io.github.smiley4.ktorswaggerui.dsl

import com.fasterxml.jackson.core.type.TypeReference
import java.lang.reflect.Type

/**
 * Describes a single request/response body with multipart content.
 * See https://swagger.io/docs/specification/describing-request-body/multipart-requests/ for more info
 */
@OpenApiDslMarker
class OpenApiMultipartBody : OpenApiBaseBody() {

    private val parts = mutableListOf<OpenapiMultipartPart>()


    /**
     * One part of a multipart-body
     */
    fun part(name: String, type: Type, block: OpenapiMultipartPart.() -> Unit) {
        parts.add(OpenapiMultipartPart(name, type).apply(block))
    }


    /**
     * One part of a multipart-body
     */
    fun part(name: String, type: Type) = part(name, type) {}


    /**
     * One part of a multipart-body
     */
    inline fun <reified TYPE> part(name: String) = part(name, object : TypeReference<TYPE>() {}.type)


    /**
     * One part of a multipart-body
     */
    inline fun <reified TYPE> part(name: String, noinline block: OpenapiMultipartPart.() -> Unit) =
        part(name, object : TypeReference<TYPE>() {}.type, block)


    /**
     * One part of a multipart-body
     */
    fun part(name: String, customSchema: CustomSchemaRef, block: OpenapiMultipartPart.() -> Unit) {
        parts.add(OpenapiMultipartPart(name, null).apply(block).apply {
            this.customSchema = customSchema
        })
    }


    /**
     * One part of a multipart-body
     */
    fun part(name: String, customSchema: CustomSchemaRef) = part(name, customSchema) {}


    /**
     * One part of a multipart-body
     */
    fun part(name: String, customSchemaId: String, block: OpenapiMultipartPart.() -> Unit) = part(name, obj(customSchemaId), block)


    /**
     * One part of a multipart-body
     */
    fun part(name: String, customSchemaId: String) = part(name, customSchemaId) {}

    fun getParts(): List<OpenapiMultipartPart> = parts

}