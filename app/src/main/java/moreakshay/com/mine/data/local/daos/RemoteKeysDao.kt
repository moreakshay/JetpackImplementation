package moreakshay.com.mine.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.mine.data.local.entities.MovieRemoteKeysEntity
import moreakshay.com.mine.data.local.entities.TeleRemoteKeysEntity
import moreakshay.com.mine.utils.constants.KEY_ID
import moreakshay.com.mine.utils.constants.MOVIE_REMOTE_KEY
import moreakshay.com.mine.utils.constants.TELE_REMOTE_KEY

@Dao
interface MovieRemoteKeysDao : BaseDao<MovieRemoteKeysEntity>{
    @Query("SELECT * FROM $MOVIE_REMOTE_KEY WHERE $KEY_ID = :keyId")
    suspend fun remoteKeysRepoId(keyId: Int): MovieRemoteKeysEntity?

    @Query("DELETE FROM $MOVIE_REMOTE_KEY")
    suspend fun clearRemoteKeys()
}

@Dao
interface TeleRemoteKeysDao : BaseDao<TeleRemoteKeysEntity>{
    @Query("SELECT * FROM $TELE_REMOTE_KEY WHERE $KEY_ID = :keyId")
    suspend fun remoteKeysRepoId(keyId: Int): TeleRemoteKeysEntity?

    @Query("DELETE FROM $TELE_REMOTE_KEY")
    suspend fun clearRemoteKeys()
}