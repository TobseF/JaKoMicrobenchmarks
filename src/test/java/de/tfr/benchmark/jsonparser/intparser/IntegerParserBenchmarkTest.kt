package de.tfr.benchmark.jsonparser.intparser

import de.tfr.benchmark.jsonparser.CustomJsonIntArrayParser
import gnu.trove.list.array.TCharArrayList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class IntegerParserBenchmarkTest {
  @Test
  fun testParseInt() {
    assertEquals(123, CustomJsonIntArrayParser.parseInt(TCharArrayList("123".toCharArray())))
  }

  @Test
  fun testCustomParseInt() {
    assertEquals(123, Integer.parseInt("123"))
  }
}