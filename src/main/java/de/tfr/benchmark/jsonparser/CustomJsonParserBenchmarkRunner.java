package de.tfr.benchmark.jsonparser;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class CustomJsonParserBenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(CustomJsonParserBenchmark.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}