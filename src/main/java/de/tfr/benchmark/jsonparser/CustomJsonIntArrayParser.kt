package de.tfr.benchmark.jsonparser

import gnu.trove.TIntCollection
import gnu.trove.list.array.TCharArrayList
import gnu.trove.list.array.TIntArrayList

object CustomJsonIntArrayParser {


  /**
   * Parse a JSON array of integers into an integer collection [TIntCollection].
   */
  fun parseJsonArrayAsList(json: String): TIntArrayList {
    return parseJsonArray(json, TIntArrayList())
  }

  /**
   * Fills a JSON array of integers into the given integer collection.
   */
  private fun <T : TIntCollection> parseJsonArray(json: String, collection: T): T {
    val nextNumber = TCharArrayList()
    json.forEach {
      if ((it >= '0' && it <= '9') || it == '-') {
        nextNumber.add(it)
      } else if (it == ',') {
        if (!nextNumber.isEmpty) {
          collection.add(parseInt(nextNumber))
          nextNumber.clear()
        }
      }
    }
    if (!nextNumber.isEmpty) {
      collection.add(parseInt(nextNumber))
    }
    return collection
  }

  /**
   * Parses a String into an Int.
   */
  fun parseInt(str: TCharArrayList): Int {
    var ival = 0
    var idx = 0
    var end: Int = 0
    var sign = false
    var ch: Char = '0'
    if (str.size().also { end = it } == 0 || ((str[0].also { ch = it } < '0' || ch > '9')
              && (!(ch == '-').also { sign = it } || ++idx == end || str[idx].also { ch = it } < '0' || ch > '9')))
      throw NumberFormatException("Failed parsing text as number: $str")
    while (true) {
      ival += '0'.code - ch.code
      if (++idx == end) return if (sign) ival else -ival
      if (str[idx].also { ch = it } < '0' || ch > '9') throw NumberFormatException(str.toString())
      ival *= 10
    }
  }


}