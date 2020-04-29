package cl.svasquezm.sandbox.features.room.base.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.svasquezm.sandbox.features.room.base.PostModel

@Dao
interface PostDAO {
    @Query("SELECT * FROM PostModel")
    fun getPosts(): List<PostModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostModel)
}
