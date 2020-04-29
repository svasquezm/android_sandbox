package cl.svasquezm.sandbox.features.room.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.svasquezm.sandbox.features.room.base.PostModel

@Database(entities = [PostModel::class], version = 1, exportSchema = true)
abstract class PostDatabase : RoomDatabase() {
    abstract fun dao(): PostDAO
}