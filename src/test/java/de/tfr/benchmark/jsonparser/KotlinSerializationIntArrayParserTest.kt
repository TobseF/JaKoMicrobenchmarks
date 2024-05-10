package de.tfr.benchmark.jsonparser

import gnu.trove.set.hash.TIntHashSet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinSerializationIntArrayParserTest {
  @Test
  fun testParseJsonArrayAsList() {
    val parsed = KotlinSerializationIntArrayParser.parseJsonArrayAsList("[100,101,102]")
    assertEquals(TIntHashSet().apply { addAll(listOf(100, 101, 102)) }, parsed)
  }
}