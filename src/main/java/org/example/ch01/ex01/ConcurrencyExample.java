package org.example.ch01.ex01;

import java.util.ArrayList;

public class ConcurrencyExample {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors() * 2;
//        int cpuCores = Runtime.getRuntime().availableProcessors() + 1;

        // CPU 개수를 초과하는 데이터 생성
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // CPU 개수를 초과하는(2배) 데이터를 병렬로 처리
        long startTime2 = System.currentTimeMillis();
        long sum2 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        // 병렬 처리되지만 CPU의 개수보다 데이터 개수가 2배 많으므로 시간이 더 걸릴듯
                        // 그런데 2배가 아니라 1개만 많게 해줘도 비슷하게 시간이 더 걸림!! -> 동시성
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return i * i;
                })
                .sum();

        long endTime2 = System.currentTimeMillis();

        System.out.println("CPU 개수만큼 데이터를 병렬로 처리하는 데 걸릴 시간: " + (endTime2 - startTime2) + "ms");
        System.out.println("결과1 = " + sum2);

        /* 출력 결과
            CPU 개수만큼 데이터를 병렬로 처리하는 데 걸릴 시간: 1015ms
            결과1 = 204
         */
    }
}
