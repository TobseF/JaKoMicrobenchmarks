package de.tfr.benchmark.jsonparser.intparser;

import gnu.trove.list.array.TCharArrayList;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@State(Scope.Benchmark)
public class ExecutionPlan {

    static int startId = 420000000;
    @Param({"10", "100", "1000", "10000"})
    public int iterations;
    private List<String> numbers;
    private List<TCharArrayList> numbersAsChars;

    public int getIterations() {
        return iterations;
    }


    public List<String> getNumbers() {
        return numbers;
    }

    public List<TCharArrayList> getNumbersAsChars() {
        return numbersAsChars;
    }

    @Setup
    public void setup() {
        numbers = LongStream.range(startId, startId + iterations).mapToObj(String::valueOf).collect(toList());
        numbersAsChars = numbers.stream().map(it -> new TCharArrayList(it.toCharArray())).collect(toList());
    }

}