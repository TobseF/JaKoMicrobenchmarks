/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.tfr.benchmark.numbersuffix;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NumberSuffixBenchmark {

    /**
     * @return the last three digits of the given number
     */
    public static int getPrefixByMath(long id) {
        long plzId = id / 1000L;
        if (id >= 0) {
            plzId = -plzId;
        }
        return (int) plzId;
    }

    /**
     * @return the last three digits of the given number
     */
    public static int getPrefixByString(long id) {
        String idAsString = String.valueOf(id);
        return Integer.parseInt((id >= 0 ? "-" : "") +
                idAsString.substring(0, idAsString.length() - TestEntity.SUFFIX.length()));
    }

    /**
     * Cuts of the last 3 digits of a number.
     * Uses {@link Integer#parseInt(String)} and {@link String#substring(int, int)}.
     * Sample input data: `1000999`
     * Sample output data: `1000`
     */
    @Fork(value = 1, warmups = 2)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 10)
    @Measurement(iterations = 3, time = 2)
    public void getPrefixByString(TestEntity plan, Blackhole blackhole) {
        for (int i = 0; i < plan.getIterations(); i++) {
            blackhole.consume(getPrefixByString(plan.getId()));
        }
    }

    /**
     * Cuts of the last 3 digits of a number.
     * Uses math operations to get the result.
     * Sample input data: `1000999`
     * Sample output data: `1000`
     */
    @Fork(value = 1, warmups = 2)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 10)
    @Measurement(iterations = 3, time = 2)
    public void getPrefixByMath(TestEntity plan, Blackhole blackhole) {
        for (int i = 0; i < plan.getIterations(); i++) {
            blackhole.consume(getPrefixByMath(plan.getId()));
        }
    }

}
