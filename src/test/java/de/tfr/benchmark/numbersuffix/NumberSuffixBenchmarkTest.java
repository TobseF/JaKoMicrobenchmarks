package de.tfr.benchmark.numbersuffix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSuffixBenchmarkTest {
    @Test
    void testGetSuffixByMath() {
        assertEquals(123, NumberSuffixBenchmark.getPrefixByMath(1000123));
    }

    @Test
    void testGetSuffixByString() {
        assertEquals(123, NumberSuffixBenchmark.getPrefixByString(1000123));
    }
}