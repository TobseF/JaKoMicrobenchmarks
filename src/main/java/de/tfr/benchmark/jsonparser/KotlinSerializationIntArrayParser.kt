package de.tfr.benchmark.jsonparser

import gnu.trove.set.TIntSet
import gnu.trove.set.hash.TIntHashSet
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.ResolvableType
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.MediaType
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import reactor.core.publisher.Mono
import java.nio.charset.Charset
import java.time.Duration

object KotlinSerializationIntArrayParser {
  val bufferFactory = DefaultDataBufferFactory()

  fun parseJsonArrayAsList(json: String): TIntSet {
    val decoder = KotlinSerializationJsonDecoder()
    val type = object : ParameterizedTypeReference<List<Int>>() {}
    val data: Mono<DataBuffer> = Mono.just(json.toByteBuffer())
    val decode: Mono<Any> = decoder.decodeToMono(data, ResolvableType.forType(type), MediaType.APPLICATION_JSON, null)

    @Suppress("UNCHECKED_CAST")
    val decodedList: List<Int> = decode.block(Duration.ofMillis(200)) as List<Int>
    return TIntHashSet(decodedList)
  }

  private fun String.toByteBuffer(charset: Charset = Charset.defaultCharset()): DataBuffer {
    return bufferFactory.wrap(this.toByteArray(charset))
  }


}