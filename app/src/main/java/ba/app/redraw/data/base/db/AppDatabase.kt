package ba.app.redraw.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ba.app.redraw.data.user.User
import ba.app.redraw.data.user.UsersDao
import java.util.Date
import javax.inject.Singleton

@Singleton
@TypeConverters(value = [DateTypeConverter::class])
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

object DateTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toLong(value: Date?): Long? {
        return value?.time
    }
}
