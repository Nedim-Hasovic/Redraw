package ba.app.redraw.data.base.di.api

/**
 * This represents something that can produce implementation of API
 */
interface ApiFactory {

    /**
     * Should produce instance of [T]
     */
    fun <T> buildApi(type: Class<T>): T
}
