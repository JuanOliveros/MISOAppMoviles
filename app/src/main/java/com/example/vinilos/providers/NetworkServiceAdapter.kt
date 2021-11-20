package com.example.vinilos.providers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.*
import com.fasterxml.jackson.module.kotlin.*
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
                var listTracks = mutableListOf<Track>()
                var listPerformers = mutableListOf<Performer>()
                var listComments = mutableListOf<Comment>()
                var item:JSONObject? = null
                //var tracks:JSONArray? = null

                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)

                    /*tracks = item.getJSONArray("tracks")
                    for (j in 0 until tracks.length()) {
                        val itemT = tracks.getJSONObject(j)
                        listTracks.add(j, Track(id = itemT.getInt("id"),duration = itemT.getString("duration"),name = itemT.getString("name")))
                    }*/

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
                for (i in 0 until tracksArray.length()) {
                    track = tracksArray.getJSONObject(i)
                    tracksList.add(i, Track(id = track.getInt("id"), name = track.getString("name"), duration = track.getString("duration")))
                }

                val listAlbums = mutableListOf<Album>()
                val listPrizes = mutableListOf<Prize>()
                val listMusicians = mutableListOf<String>()
                val performersArray = JSONArray(item.get("performers").toString())
                for (i in 0 until performersArray.length()) {
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
                for (i in 0 until commentsArray.length()) {
                    comment = commentsArray.getJSONObject(i)
                    commentsList.add(i, Comment(id = comment.getInt("id"), description = comment.getString("description"), rating = comment.getString("rating")))
                }

                var album = Album(
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
                var listCA : List<CollectorAlbum> = listOf()
                var listComments : List<Comment> = listOf()
                var listFP : List<Performer> = listOf()
                var item:JSONObject? = null

                for (i in 0 until resp.length()) {
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

    suspend fun getArtists() = suspendCoroutine<List<Performer>>{ cont->
        requestQueue.add(getRequest("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Performer>()
                var listAlbums : List<Album> = listOf()
                var listPrizes : List<Prize> = listOf()
                var listMusicians : List<String> = listOf()
                var item:JSONObject? = null

                for (i in 0 until resp.length()) {
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
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.PUT, BASE_URL + path, body, responseListener, errorListener)
    }
}