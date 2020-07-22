package com.ly.jmh.sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

/**
 * @author whq46936
 * @version Id: ListBenchmark, v 0.1 2020/7/19 21:00 whq46936 Exp $
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@Fork(1)
public class ListBenchmark {

    @State(Scope.Thread)
    public static class ArrayClass {
        public List<String> list;

        @Setup(Level.Trial)
        public void setup() {
            list = new ArrayList<>();
        }

        @TearDown
        public void tearDown() {
            System.out.println("arrayList size:" + list.size());
        }
    }

    @State(Scope.Thread)
    public static class LinkedClass {
        public List<String> list;

        @Setup(Level.Trial)
        public void setup() {
            list = new LinkedList<>();
        }

        @TearDown
        public void tearDown() {
            System.out.println("linkedList size:" + list.size());
        }
    }



    @Benchmark
    public List<String> testArray(ArrayClass arrayClass) {
        arrayClass.list.add("1");
        return arrayClass.list;
    }

    @Benchmark
    public List<String> testLinked(LinkedClass linkedClass) {
        linkedClass.list.add("1");
        return linkedClass.list;
    }
}