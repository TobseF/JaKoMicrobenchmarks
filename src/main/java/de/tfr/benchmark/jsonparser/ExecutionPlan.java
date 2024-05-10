package de.tfr.benchmark.jsonparser;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.stream.LongStream;

import static java.util.stream.Collectors.joining;

@State(Scope.Benchmark)
public class ExecutionPlan {

    static int startId = 420000000;
    @Param({"10", "100", "1000", "10000"})
    public int iterations;
    private String json;

    public int getIterations() {
        return iterations;
    }

    public String getJson() {
        return json;
    }

    @Setup
    public void setup() {
        json = "[" + LongStream.range(startId, startId + iterations).mapToObj(String::valueOf).collect(joining(",")) + "]";
    }

}