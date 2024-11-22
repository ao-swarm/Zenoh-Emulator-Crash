package com.example.zenohemulatorcrash

import io.zenoh.pubsub.Subscriber
import io.zenoh.sample.Sample
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

data class ZSub(
    val subscriber: Subscriber<Channel<Sample>>,
    val sampleFlow: Flow<Sample>,
) {
    fun undeclare() {
        subscriber.undeclare()
        subscriber.keyExpr.close()
    }
}