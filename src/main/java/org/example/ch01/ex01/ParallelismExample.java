package org.example.ch01.ex01;

import java.util.ArrayList;

public class ParallelismExample {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU 개수: " + cpuCores + "\n");  // 내 컴퓨터의 경우 8개

        // CPU 개수만큼 데이터 생성
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // 병렬이 아니라 그냥 순차적으로 처리
        long startTime = System.currentTimeMillis();
        long sum = data.stream()
                .mapToLong(i -> {
                    try {
                        // 0.5초씩 8개의 데이터를 처리하므로 4초 내외로 걸릴 것
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return i * i;
                })
                .sum();

        long endTime = System.currentTimeMillis();

        System.out.println("CPU 개수만큼 데이터를 순차적으로 처리하는 데 걸릴 시간: " + (endTime - startTime) + "ms");
        System.out.println("결과 = " + sum);

        System.out.println();

        // CPU 개수만큼 데이터 생성
        ArrayList<Integer> dataParallel = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            dataParallel.add(i);
        }

        // CPU 개수만큼 데이터를 병렬로 처리
        long startTimeParallel = System.currentTimeMillis();
        long sumParallel = dataParallel.parallelStream()
                .mapToLong(i -> {
                    try {
                        // CPU가 8개니까 각 스레드가 0.5초를 병렬로  처리하니까 0.5초 내외로 걸릴 것!
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return i * i;
                })
                .sum();

        long endTimeParallel = System.currentTimeMillis();

        System.out.println("CPU 개수만큼 데이터를 병렬로 처리하는 데 걸릴 시간: " + (endTimeParallel - startTimeParallel) + "ms");
        System.out.println("결과 = " + sumParallel);

        /*  출력 결과
                CPU 개수: 8

                CPU 개수만큼 데이터를 순차적으로 처리하는 데 걸릴 시간: 4029ms
                결과 = 140

                CPU 개수만큼 데이터를 병렬로 처리하는 데 걸릴 시간: 521ms
                결과 = 140
         */
    }
}
