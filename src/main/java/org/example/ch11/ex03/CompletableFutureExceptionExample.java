package org.example.ch11.ex03;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionExample {
    public static void main(String[] args) {
        CompletableFuture<Object> handle = CompletableFuture.supplyAsync(() -> 2)
                .thenApplyAsync(result -> result + 3)
                .thenApplyAsync(result -> {
                    throw new RuntimeException("error");
                })
                .handle((result, exception) -> {
                    if (exception != null) {
                        System.out.println("에외 발생!! : " + exception.getMessage());
                        return 0;
                    } else {
                        return result;
                    }
                });

        System.out.println("최종 결과: " + handle.join());
    }
}
