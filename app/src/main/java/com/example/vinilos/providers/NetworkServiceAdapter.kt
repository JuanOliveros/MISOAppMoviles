package com.example.vinilos.providers

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://misw4203-api-vinilos-equipo12.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                val listTracks = mutableListOf<Track>()
                val listPerformers = mutableListOf<Performer>()
                val listComments = mutableListOf<Comment>()
                var item:JSONObject? = null

                val respSize = resp.length()
                for (i in 0 until respSize) {
                    item = resp.getJSONObject(i)

                    list.add(i, Album(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        cover = item.getString("cover"),
                        releaseDate = item.getString("releaseDate"),
                        description = item.getString("description"),
                        genre = item.getString("genre"),
                        recordLabel = item.getString("recordLabel"),
                        tracks = listTracks,
                        performers = listPerformers,
                        comments = listComments)
                    )
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getAlbum(id: Int) = suspendCoroutine<Album>{ cont->
        requestQueue.add(getRequest("albums/$id",
            Response.Listener<String> { response ->
                val item = JSONObject(response)
                val tracksList = mutableListOf<Track>()
                val performersList = mutableListOf<Performer>()
                val commentsList = mutableListOf<Comment>()
                var track:JSONObject? = null
                var performer:JSONObject? = null
                var comment:JSONObject? = null

                val tracksArray = JSONArray(item.get("tracks").toString())
                val tracksArrayLength = tracksArray.length()
                for (i in 0 until tracksArrayLength) {
                    track = tracksArray.getJSONObject(i)
                    tracksList.add(i, Track(id = track.getInt("id"), name = track.getString("name"), duration = track.getString("duration")))
                }

                val listAlbums = mutableListOf<Album>()
                val listPrizes = mutableListOf<Prize>()
                val listMusicians = mutableListOf<String>()
                val performersArray = JSONArray(item.get("performers").toString())
                val performersArrayLength = performersArray.length()
                for (i in 0 until performersArrayLength) {
                    performer = performersArray.getJSONObject(i)
                    performersList.add(i, Performer(
                        id = performer.getInt("id"),
                        name = performer.getString("name"),
                        image = performer.getString("image"),
                        description = performer.getString("description"),
                        birthDate = performer.optString("birthDate"),
                        creationDate = performer.optString("creationDate"),
                        albums = listAlbums,
                        performerPrizes = listPrizes,
                        musicians = listMusicians
                    ))
                }

                val commentsArray = JSONArray(item.get("comments").toString())
                val commentsArrayLength = commentsArray.length()
                for (i in 0 until commentsArrayLength) {
                    comment = commentsArray.getJSONObject(i)
                    commentsList.add(i, Comment(id = comment.getInt("id"), description = comment.getString("description"), rating = comment.getString("rating")))
                }

                val album = Album(
                    id = item.getInt("id"),
                    name = item.getString("name"),
                    cover = item.getString("cover"),
                    releaseDate = item.getString("releaseDate"),
                    description = item.getString("description"),
                    genre = item.getString("genre"),
                    recordLabel = item.getString("recordLabel"),
                    tracks = tracksList,
                    performers = performersList,
                    comments = commentsList
                )
                cont.resume(album)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>>{ cont->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                val listCA : List<CollectorAlbum> = listOf()
                val listComments : List<Comment> = listOf()
                val listFP : List<Performer> = listOf()
                var item:JSONObject? = null

                val respLength = resp.length()
                for (i in 0 until respLength) {
                    item = resp.getJSONObject(i)
                    list.add(i, Collector(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        telephone = item.getString("telephone"),
                        email = item.getString("email"),
                        collectorAlbums = listCA,
                        comments = listComments,
                        favoritePerformers = listFP)
                    )
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollector(id: Int) = suspendCoroutine<Collector>{ cont->
        requestQueue.add(getRequest("collectors/$id",
            Response.Listener<String> { response ->
                val item = JSONObject(response)
                val albumsList = mutableListOf<CollectorAlbum>()
                val performersList = mutableListOf<Performer>()
                val commentsList = mutableListOf<Comment>()
                var performer:JSONObject? = null
                var comment:JSONObject? = null

                val listAlbums = mutableListOf<Album>()
                val listPrizes = mutableListOf<Prize>()
                val listMusicians = mutableListOf<String>()
                val performersArray = JSONArray(item.get("favoritePerformers").toString())
                val performersArrayLength = performersArray.length()
                for (i in 0 until performersArrayLength) {
                    performer = performersArray.getJSONObject(i)
                    performersList.add(i, Performer(
                        id = performer.getInt("id"),
                        name = performer.getString("name"),
                        image = performer.getString("image"),
                        description = performer.getString("description"),
                        birthDate = performer.optString("birthDate"),
                        creationDate = performer.optString("creationDate"),
                        albums = listAlbums,
                        performerPrizes = listPrizes,
                        musicians = listMusicians
                    ))
                }

                val commentsArray = JSONArray(item.get("comments").toString())
                val commentsArrayLength = commentsArray.length()
                for (i in 0 until commentsArrayLength) {
                    comment = commentsArray.getJSONObject(i)
                    commentsList.add(i, Comment(id = comment.getInt("id"), description = comment.getString("description"), rating = comment.getString("rating")))
                }

                val collector = Collector(
                    id = item.getInt("id"),
                    name = item.getString("name"),
                    telephone = item.getString("telephone"),
                    email = item.getString("email"),
                    collectorAlbums = albumsList,
                    comments = commentsList,
                    favoritePerformers = performersList)
                cont.resume(collector)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getArtists() = suspendCoroutine<List<Performer>>{ cont->
        requestQueue.add(getRequest("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Performer>()
                val listAlbums : List<Album> = listOf()
                val listPrizes : List<Prize> = listOf()
                val listMusicians : List<String> = listOf()
                var item:JSONObject? = null

                val respLength = resp.length()
                for (i in 0 until respLength) {
                    item = resp.getJSONObject(i)
                    list.add(i, Performer(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        birthDate = item.optString("birthDate", ""),
                        creationDate = item.optString("creationDate", ""),
                        albums = listAlbums,
                        performerPrizes = listPrizes,
                        musicians = listMusicians)
                    )
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    /* Commented out since we're not using this code right now */
    /* But we'll probably use it in the near future */
    /*private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.PUT, BASE_URL + path, body, responseListener, errorListener)
    }*/
}