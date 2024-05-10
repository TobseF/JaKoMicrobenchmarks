package de.tfr.benchmark.numbersuffix;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class NumberSuffixBenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(NumberSuffixBenchmark.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}