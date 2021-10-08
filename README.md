# JSONExtensions
JSONExtensions - JSONUtils for Android

It has these utility methods. Helps to make manual json parsing concise and easier to read.

fun JSONArray.forEachIndexed(lambda: (index: Int) -> Unit)

fun JSONArray.forEachJSONObject(lambda: (jsonObj: JSONObject) -> Unit)

fun JSONArray.forEachIndexedJSONObject(lambda: (index: Int, jsonObj: JSONObject) -> Unit)

fun JSONArray.forEachJSONObjectWithBreak(lambda: (jsonObj: JSONObject, stopIteration: () -> Unit) -> Unit)

fun JSONArray.toStringList(): List<String>

fun JSONArray.forEachString(lambda: (value: String) -> Unit)

fun JSONArray.forEachIndexedString(lambda: (index: Int, value: String) -> Unit)

fun JSONObject.forEachJSONArray(lambda: (jsonArray: JSONArray) -> Unit)

fun JSONObject.forEachJSONObjectSorted(lambda: (jsonArray: JSONObject) -> Unit)

fun JSONObject.forEachJSONObject(lambda: (jsonArray: JSONObject) -> Unit)

fun JSONObject.forEachString(lambda: (key: String, value: String) -> Unit)

fun JSONObject.forEachIndexedStringWithKeys(lambda: (index: Int, key: String, value: String) -> Unit)

fun JSONObject.forEachJSONArrayWithKeys(lambda: (key: String, jsonArray: JSONArray) -> Unit

fun JSONObject.getOrEmptyJSONObject(name: String): JSONObject

fun JSONObject.getOrEmptyJSONArray(name: String): JSONArray

fun JSONObject.optJSONObject(name: String, fallback: JSONObject): JSONObject

fun JSONObject.optJSONArray(name: String, fallback: JSONArray): JSONArray

inline val String.asJsonObjOrNull: JSONObject?

inline val String.asJsonObj: JSONObject

inline val String.asJsonArray: JSONArray

fun JSONArray.isEmpty(): Boolean

fun JSONArray.isNotEmpty(): Boolean

fun List<JSONObject>.merge(): JSONObject

fun List<JSONArray>.mergeAndSort(): JSONArray
