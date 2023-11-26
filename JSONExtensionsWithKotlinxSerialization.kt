import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

val JsonElement.jsonArrayOrNull: JsonArray?
    get() = if (this is JsonArray) this else null

val JsonElement.jsonObjectOrNull: JsonObject?
    get() = if (this is JsonObject) this else null

fun JsonObject.optJsonObject(name: String): JsonObject? {
    return get(name)?.jsonObjectOrNull
}

fun JsonObject.optInt(name: String, defaultValue: Int = -1): Int {
    return get(name)?.jsonPrimitive?.intOrNull ?: defaultValue
}

fun JsonObject.getInt(name: String): Int {
    return get(name)!!.jsonPrimitive.int
}

fun JsonObject.optBoolean(name: String, defaultValue: Boolean = false): Boolean {
    return get(name)?.jsonPrimitive?.booleanOrNull ?: defaultValue
}

fun JsonObject.getBoolean(name: String): Boolean {
    return get(name)!!.jsonPrimitive.boolean
}

fun JsonObject.optString(name: String, defaultValue: String = ""): String {
    return get(name)?.jsonPrimitive?.content ?: defaultValue
}

fun JsonObject.getString(name: String): String {
    return get(name)!!.jsonPrimitive.content
}

fun JsonArray.optJsonObject(index: Int): JsonObject? {
    return if (index < 0 || index >= size) {
        null
    } else {
        get(index).jsonObjectOrNull
    }
}

inline fun JsonArray.forEachJSONObject(lambda: (jsonObj: JsonObject) -> Unit) {
    forEach {
        lambda(it as JsonObject)
    }
}

inline fun JsonArray.forEachIndexedJSONObject(lambda: (index: Int, jsonObj: JsonObject) -> Unit) {
    forEachIndexed { index, jsonElement ->
        lambda(index, jsonElement as JsonObject)
    }
}

inline fun JsonArray.forEachString(lambda: (value: String) -> Unit) {
    forEach {
        lambda(it.jsonPrimitive.content)
    }
}

inline fun JsonArray.forEachIndexedString(lambda: (index: Int, value: String) -> Unit) {
    forEachIndexed { index, jsonElement ->
        lambda(index, jsonElement.jsonPrimitive.content)
    }
}



inline fun JsonObject.forEachJSONObjectWithKeys(lambda: (key: String, jsonObject: JsonObject) -> Unit) {
    forEach { entry ->
        lambda(entry.key, entry.value.jsonObject)
    }
}

inline fun JsonObject.forEachJSONObject(lambda: (jsonObject: JsonObject) -> Unit) {
    forEach { entry ->
        lambda(entry.value.jsonObject)
    }
}

inline fun JsonObject.forEachString(lambda: (key: String, value: String) -> Unit) {
    forEach { entry ->
        lambda(entry.key, entry.value.jsonPrimitive.content)
    }
}

inline fun JsonObject.forEachIndexedStringWithKeys(lambda: (index: Int, key: String, value: String) -> Unit) {
    var index = 0
    forEach { entry ->
        lambda(index, entry.key, entry.value.jsonPrimitive.content)
        index++
    }
}

inline fun JsonObject.forEachJSONArrayWithKeys(lambda: (key: String, jsonArray: JsonArray) -> Unit) {
    forEach { entry ->
        lambda(entry.key, entry.value.jsonArray)
    }
}

fun JsonObject.getOrEmptyJSONObject(name: String): JsonObject {
    return get(name)?.jsonObjectOrNull ?: buildJsonObject {  }
}

fun JsonObject.getJSONObject(name: String): JsonObject {
    return get(name)!!.jsonObject
}

fun JsonObject.getOrEmptyJSONArray(name: String): JsonArray {
    return get(name)?.jsonArrayOrNull ?: buildJsonArray {  }
}

fun JsonObject.getJSONArray(name: String): JsonArray {
    return get(name)!!.jsonArray
}
