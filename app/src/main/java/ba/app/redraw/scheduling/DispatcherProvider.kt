package ba.app.redraw.scheduling

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Provider for [CoroutineDispatcher]s used in application
 */
interface DispatcherProvider {
    /**
     * I/O scheduler - this should be a background thread
     */
    fun io(): CoroutineDispatcher

    /**
     * Main scheduler - for UI - connected to main thread
     */
    fun main(): CoroutineDispatcher

    /**
     * Computation scheduler - background thread but should not be used for I/O
     * It should rather be used for CPU heavy computation work
     */
    fun computation(): CoroutineDispatcher

    /**
     * Single threaded background scheduler
     */
    fun single(): CoroutineDispatcher
}
