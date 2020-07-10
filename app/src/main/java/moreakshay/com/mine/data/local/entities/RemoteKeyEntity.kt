package moreakshay.com.mine.data.local.entities

import androidx.room.Entity
import moreakshay.com.mine.utils.constants.KEY_ID
import moreakshay.com.mine.utils.constants.MOVIE_REMOTE_KEY
import moreakshay.com.mine.utils.constants.TELE_REMOTE_KEY

@Entity(tableName = MOVIE_REMOTE_KEY, primaryKeys = [KEY_ID])
data class MovieRemoteKeysEntity(
        val id: Int,
        val prev_key: Int?,
        val next_key: Int?
)

@Entity(tableName = TELE_REMOTE_KEY, primaryKeys = [KEY_ID])
data class TeleRemoteKeysEntity(
        val id: Int,
        val prev_key: Int?,
        val next_key: Int?
)