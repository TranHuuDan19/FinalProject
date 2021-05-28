package com.example.myapplication

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.myapplication.PermissionsUtil.checkPermissions
import com.example.myapplication.PermissionsUtil.requestPermissions
import java.util.*


class MainActivity : AppCompatActivity() {
    private val STORAGE_PERMISSION_ID = 0
    private val mSongList: MutableList<Song> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        if (!checkStorePermission(STORAGE_PERMISSION_ID)) {
            showRequestPermission(STORAGE_PERMISSION_ID)
        }
    }//get columns

    //add songs to list
    //retrieve item_song info
    val songList: Unit
        get() {
            //retrieve item_song info
            val musicResolver = contentResolver
            val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val musicCursor = musicResolver.query(musicUri, null, null, null, null)
            if (musicCursor != null && musicCursor.moveToFirst()) {
                //get columns
                val titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
                val albumID = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
                val artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                val songLink = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                //add songs to list
                do {
                    val thisId = musicCursor.getLong(idColumn)
                    val thisTitle = musicCursor.getString(titleColumn)
                    val thisArtist = musicCursor.getString(artistColumn)
                    val thisSongLink = Uri.parse(musicCursor.getString(songLink))
                    val some = musicCursor.getLong(albumID)
                    val uri = ContentUris.withAppendedId(sArtworkUri, some)
                    mSongList.add(
                        Song(
                            thisId, thisTitle, thisArtist, uri.toString(),
                            thisSongLink.toString()
                        )
                    )
                } while (musicCursor.moveToNext())
            }
            assert(musicCursor != null)
            musicCursor!!.close()
            Toast.makeText(this, mSongList.size.toString() + " Songs Found!!!", Toast.LENGTH_SHORT)
                .show()
        }

    private fun checkStorePermission(permission: Int): Boolean {
        return if (permission == STORAGE_PERMISSION_ID) {
            checkPermissions(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } else {
            true
        }
    }

    private fun showRequestPermission(requestCode: Int) {
        val permissions: Array<String>
        permissions = if (requestCode == STORAGE_PERMISSION_ID) {
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        } else {
            arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
        requestPermissions(this, requestCode, *permissions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 0) {
            var i = 0
            val len = permissions.size
            while (i < len) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    songList
                    return
                }
                i++
            }
        }
    }

    companion object {
        private val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
    }
}