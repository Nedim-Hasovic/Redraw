package ba.app.redraw.data.models

/**
 * Singleton for future data handling
 */

class CanvasSingleton private constructor() {

    companion object {
        private var instance: CanvasSingleton? = null

        fun getInstance(): CanvasSingleton? {
            if (instance == null) {
                instance = CanvasSingleton()
            }
            return instance
        }

        fun clear() {
            instance = null
        }
    }
}
