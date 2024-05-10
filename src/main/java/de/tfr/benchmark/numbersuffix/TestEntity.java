package de.tfr.benchmark.numbersuffix;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class TestEntity {

    static long id = 4200000000L;
    static String SUFFIX = "999";

    @Param({ "10", "100", "1000", "10000"})
    public int iterations;

    public long getId() {
        return id;
    }

    public int getIterations() {
        return iterations;
    }
}