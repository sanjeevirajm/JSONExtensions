import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.forEachIndexed(lambda: (index: Int) -> Unit) {
    for (i in 0 until length()) {
        lambda(i)
    }
}

fun JSONArray.forEachJSONObject(lambda: (jsonObj: JSONObject) -> Unit) {
    for (i in 0 until length()) {
        lambda(getJSONObject(i))
    }
}

fun JSONArray.forEachIndexedJSONObject(lambda: (index: Int, jsonObj: JSONObject) -> Unit) {
    for (i in 0 until length()) {
        lambda(i, getJSONObject(i))
    }
}

fun JSONArray.forEachJSONObjectWithBreak(lambda: (jsonObj: JSONObject, stopIteration: () -> Unit) -> Unit) {
    var i = 0
    val jsonLength = length()
    val stopIteration = {
        i = jsonLength
    }
    while (i < jsonLength) {
        lambda(getJSONObject(i), stopIteration)
        i++
    }
}

fun JSONArray.toStringList(): List<String> {
    val list = ArrayList<String>(length())
    forEachString {
        list.add(it)
    }
    return list
}

fun JSONArray.forEachString(lambda: (value: String) -> Unit) {
    for (i in 0 until length()) {
        lambda(getString(i))
    }
}

fun JSONArray.forEachIndexedString(lambda: (index: Int, value: String) -> Unit) {
    for (i in 0 until length()) {
        lambda(i, getString(i))
    }
}

fun JSONObject.forEachJSONArray(lambda: (jsonArray: JSONArray) -> Unit) {
    val keys = keys()
    while (keys.hasNext()) {
        lambda(getJSONArray(keys.next()))
    }
}

fun JSONObject.forEachJSONObjectSorted(lambda: (jsonArray: JSONObject) -> Unit) {
    val keys = keys()
    val arrayList = ArrayList<String>()

    while (keys.hasNext()) {
        arrayList.add(keys.next())
    }

    arrayList.sorted().forEach { key ->
        lambda(getJSONObject(key))
    }
}

fun JSONObject.forEachJSONObject(lambda: (jsonArray: JSONObject) -> Unit) {
    val keys = keys()

    while (keys.hasNext()) {
        lambda(getJSONObject(keys.next()))
    }
}

fun JSONObject.forEachString(lambda: (key: String, value: String) -> Unit) {
    val keys = keys()
    while (keys.hasNext()) {
        val key = keys.next()
        lambda(key, getString(key))
    }
}

fun JSONObject.forEachIndexedStringWithKeys(lambda: (index: Int, key: String, value: String) -> Unit) {
    val keys = keys()
    var index = 0
    while (keys.hasNext()) {
        val key = keys.next()
        lambda(index, key, getString(key))
        index++
    }
}

fun JSONObject.forEachJSONArrayWithKeys(lambda: (key: String, jsonArray: JSONArray) -> Unit) {
    val keys = keys()
    while (keys.hasNext()) {
        val key = keys.next()
        lambda(key, getJSONArray(key))
    }
}

fun JSONObject.getOrEmptyJSONObject(name: String): JSONObject
{
    return optJSONObject(name) ?: JSONObject()
}

fun JSONObject.getOrEmptyJSONArray(name: String): JSONArray
{
    return optJSONArray(name) ?: JSONArray()
}
fun JSONObject.optJSONObject(name: String, fallback: JSONObject): JSONObject
{
    return optJSONObject(name) ?: fallback
}

fun JSONObject.optJSONArray(name: String, fallback: JSONArray): JSONArray
{
    return optJSONArray(name) ?: fallback
}

inline val String.asJsonObjOrNull: JSONObject?
    get()
    {
        var jsonObj: JSONObject? = null
        if (this.isEmpty())
        {
            return jsonObj
        }
        try
        {
            jsonObj = JSONObject(this)
        }
        catch (e: Exception)
        {
            //            KotlinUtils.printStackTrace(e)
        }
        return jsonObj
    }

inline val String.asJsonObj: JSONObject
    get()
    {
        return JSONObject(this)
    }

inline val String.asJsonArray: JSONArray
    get()
    {
        return JSONArray(this)
    }

fun JSONArray.isEmpty(): Boolean {
    return this.length() == 0
}

fun JSONArray.isNotEmpty(): Boolean {
    return this.length() > 0
}

fun List<JSONObject>.merge(): JSONObject {
    val jsonObject = JSONObject()
    val jsonObjects = this
    for (temp in jsonObjects) {
        val keys = temp.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            jsonObject.put(key, temp[key])
        }
    }
    return jsonObject
}

fun List<JSONArray>.mergeAndSort(): JSONArray {
    val jsonArrays = this
    val mergedJsonArray = JSONArray()
    val list = java.util.ArrayList<Pair<Int, Any>>()

    for (temp in jsonArrays) {
        val size = temp.length()
        var i = 0
        while (i < size) {
            list.add(Pair(i, temp.get(i)))
            i++
        }
    }

    list.sortWith { o1, o2 ->
        if (o1 == null || o2 == null) 0
        else o1.component1()
            .compareTo(o2.component1())
    }


    list.forEach {
        mergedJsonArray.put(it.second)
    }

    return mergedJsonArray
}
