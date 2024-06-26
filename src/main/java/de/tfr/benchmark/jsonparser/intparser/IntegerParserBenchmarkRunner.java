package de.tfr.benchmark.jsonparser.intparser;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class IntegerParserBenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(IntegerParserBenchmark.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}