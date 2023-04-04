package org.benchmarks;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientBenchmark {

    protected static final HttpClient client = HttpClient.newBuilder().build();
    protected static final URI request250msUri = URI.create("http://mockserver:1080/benchmark/250ms");
    protected static final URI request50msUri = URI.create("http://mockserver:1080/benchmark/50ms");
    protected static final URI request1msUri = URI.create("http://mockserver:1080/benchmark/1ms");

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HttpClientBenchmark.class.getSimpleName())
                .threads(10)
                .forks(1)
                .mode(Mode.AverageTime)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void doHttpRequest250ms() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(request250msUri)
                .build();
        HttpResponse<String> httpResponse = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());
    }

    @Benchmark
    public void doHttpRequest50ms() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(request50msUri)
                .build();
        HttpResponse<String> httpResponse = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());
    }

    @Benchmark
    public void doHttpRequest1ms() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(request1msUri)
                .build();
        HttpResponse<String> httpResponse = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());
    }

}
