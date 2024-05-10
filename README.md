# <img src=".idea/icon.svg" width="36"/> Java & Kotlin Micro Benchmarks

> Collection of small micro benchmarks of Java and Kotlin snippets.

Also check out my Kotlin Multiplatform Benchmark project on GitHub:  
👉 [kotlin-multiplatform-benchmark](https://github.com/TobseF/kotlin-multiplatform-benchmark).

Benchmark tool suite for performance tests based on
[💿 JMH Java Microbenchmark Harness](https://github.com/openjdk/jmh).  
To create your own micro-benchmark, you can check out the tutorial on _Baeldung_:  
📖 [Microbenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness#types)

## 🚀 Start

### Start with Maven
Build the project:
```shell
mvn clean verify
```
Then run the generated jar:
```shell
java -jar target/benchmarks.jar
```

### Start with IDEA

To run the benchmark within your IDE, there is a Runner for each benchmark:  
**Class**: `de.tfr.benchmark.numbersuffix.NumberSuffixBenchmarkRunner`  
**Run Config**: ▶ `PidMappingsBenchmarkRunner`

### Start with JMH Plugin
Alternatively you can use the IntelliJ Plugin:  
>[💿 JMH Java Microbenchmark Harness](https://plugins.jetbrains.com/plugin/7529-jmh-java-microbenchmark-harness)

This allows starting tests like other JUnit tests:  
<img alt="JMH Idea Screenshot" src="https://plugins.jetbrains.com/files/7529/screenshot_d07532df-46b5-4fc6-a963-0864bdd84876" width="800">

## 📊 Benchmark Results

### Puid mappings

`NumberSuffixBenchmark`  
Benchmark for getting the 3 digit suffix of a number. So simply cut of the last 3 digits of a number.
Sample input data: `1000999`  
Sample output data: `1000` 
Sample output type: `int` 
The benchmark compares the cutting number method with `String.substring()` vs` a simple division.  

```text
Benchmark                      (iterations)  Mode  Cnt   Score    Error  Units
NumberSuffixBenchmark.getPrefixByString            10  avgt    3   0,001 ±  0,001  ms/op
NumberSuffixBenchmark.getPrefixByString           100  avgt    3   0,010 ±  0,001  ms/op
NumberSuffixBenchmark.getPrefixByString          1000  avgt    3   0,104 ±  0,014  ms/op
NumberSuffixBenchmark.getPrefixByString         10000  avgt    3   0,937 ±  0,368  ms/op
NumberSuffixBenchmark.getPrefixByString            10  avgt    3  ≈ 10⁻⁵           ms/op
NumberSuffixBenchmark.getPrefixByMath             100  avgt    3  ≈ 10⁻⁴           ms/op
NumberSuffixBenchmark.getPrefixByMath            1000  avgt    3   0,001 ±  0,001  ms/op
NumberSuffixBenchmark.getPrefixByMath           10000  avgt    3   0,010 ±  0,006  ms/op
```

> The `getSuffixByMath` is more than 93 times faster, than the String operations!  
> In a project, parsing of `5181276` _ids_ for one DB entity, decreased from `480`_ms_ to just `5`_ms_.   
> It was possible to save 213 Seconds for all entity (`2304183145` ids)!

### Custom Json Parser

`CustomJsonParserBenchmark`  
A custom parser for JSON int arrays vs kotlinSerialization.
The `KotlinSerializationJsonDecoder` is used internally by the Spring `WebClient`
during the execution of an HTTP request.  
Sample input data: `[100,101]`  
Sample output data: primitive list of integer.

```text
Benchmark                                (iterations)  Mode  Cnt     Score      Error  Units
NumberSuffixBenchmark.customParseJson               10  avgt    3     0,007 ±    0,001  ms/op
NumberSuffixBenchmark.customParseJson              100  avgt    3     0,461 ±    0,120  ms/op
NumberSuffixBenchmark.customParseJson             1000  avgt    3    50,073 ±    6,391  ms/op
NumberSuffixBenchmark.customParseJson            10000  avgt    3  5065,790 ± 1732,779  ms/op
NumberSuffixBenchmark.kotlinSerialization           10  avgt    3     0,019 ±    0,002  ms/op
NumberSuffixBenchmark.kotlinSerialization          100  avgt    3     0,961 ±    0,283  ms/op
NumberSuffixBenchmark.kotlinSerialization         1000  avgt    3    82,318 ±   44,921  ms/op
NumberSuffixBenchmark.kotlinSerialization        10000  avgt    3  7905,643 ± 3148,923  ms/op
```

> The custom Integer parser is 35,92% faster.

### Custom Integer Parser

`IntegerParserBenchmark`  
Custom integer primitive number parser vs `Integer.parseInt(..)`.

```text
Benchmark                              (iterations)  Mode  Cnt   Score    Error  Units
IntegerParserBenchmark.customParseInt            10  avgt    3  ≈ 10⁻⁴           ms/op
IntegerParserBenchmark.customParseInt           100  avgt    3   0,001 ±  0,001  ms/op
IntegerParserBenchmark.customParseInt          1000  avgt    3   0,015 ±  0,003  ms/op
IntegerParserBenchmark.customParseInt         10000  avgt    3   0,162 ±  0,065  ms/op
IntegerParserBenchmark.parseInt                  10  avgt    3  ≈ 10⁻³           ms/op
IntegerParserBenchmark.parseInt                 100  avgt    3   0,004 ±  0,001  ms/op
IntegerParserBenchmark.parseInt                1000  avgt    3   0,042 ±  0,050  ms/op
IntegerParserBenchmark.parseInt               10000  avgt    3   0,280 ±  1,968  ms/op
```

> The custom customParseInt ist 72,82 % faster than `Integer.parseInt()`.  
> But the absolut benefit might be small, because even 10000 Iteration tage only `0,132` _ms_.

## 🍴 Fork
This project is based on the jmh Maven template:
```shell
 mvn archetype:generate \
  -DinteractiveMode=false \
  -DarchetypeGroupId=org.openjdk.jmh \
  -DarchetypeArtifactId=jmh-java-benchmark-archetype \
  -DgroupId=de.tfr.benchmark \
  -DartifactId=redgiant-bench \
  -Dversion=1.0

```