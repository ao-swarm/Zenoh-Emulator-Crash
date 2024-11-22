package com.example.zenohemulatorcrash

import android.util.Log
import io.zenoh.Config
import io.zenoh.Session
import io.zenoh.Zenoh
import io.zenoh.pubsub.Subscriber
import io.zenoh.sample.Sample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.withContext

class ZenohClient {
    private var session: Session? = null

    /**
     * Open new Zenoh session(s).
     * Only for use on plugin load at this time.
     */
    fun openSession() {
        try {
            session = Zenoh.open(Config.default()).getOrThrow()
                .also { Log.d(TAG, "zenoh session opened") }
        } catch (e: Exception) {
            Log.e(TAG, "zenoh session failed\n $e")
        }
    }

    /** Declare a subscriber to continually receive messages.
     * Call-site manages receiving and closing.
     * @return [Result]<[ZSub]>
     */
    suspend fun subscribe(
        key: String,
        channel: Channel<Sample> = Channel(),
        onClose: (() -> Unit)? = null
    ): Result<ZSub> =
        withContext(Dispatchers.IO) {
            var subscriber: Subscriber<Channel<Sample>>? = null

            session!!.declareKeyExpr(key).mapCatching { keyExpr ->
                subscriber = session!!.declareSubscriber(
                    keyExpr,
                    channel,
                    onClose
                ).getOrThrow()

                val sampleFlow = subscriber!!.receiver.consumeAsFlow()
                ZSub(subscriber!!, sampleFlow)
            }.onFailure { e ->
                subscriber?.undeclare()
                Log.e(TAG, "Failed to subscribe - $key \n ${e.message}")
            }
        }

    /** Close the current Zenoh session(s). */
    private fun closeSession() {
        try {
            session?.close()
            Log.d(TAG, "zenoh session closed")
        } catch (e: Exception) {
            Log.e(TAG, "Error closing zenoh session\n", e)
        } finally {
            session = null
        }
    }


    companion object {
        const val TAG = "emu-crash"

        private var instance: ZenohClient? = null
        fun get(): ZenohClient = instance ?: ZenohClient().also { instance = it }

        fun dispose() {
            instance?.closeSession()
            instance = null
        }
    }
}