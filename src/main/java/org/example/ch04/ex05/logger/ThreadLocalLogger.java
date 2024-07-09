package org.example.ch04.ex05.logger;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalLogger {
    // 문자열 리스트 타입의 스레드 로컬 생성
    private static final ThreadLocal<List<String>> THREAD_LOG = ThreadLocal.withInitial(ArrayList::new);

    // 로그 저장
    public static void addLog(String log) {
        THREAD_LOG.get().add(log);
    }

    // 로그 출력
    public static void printLog() {
        List<String> logs = THREAD_LOG.get();
        System.out.println("[" + Thread.currentThread().getName() + "]: " + String.join(" -> ", logs));
    }

    // 로그 삭제
    public static void clearLog() {
        THREAD_LOG.remove();
    }

    static class ServiceA {
        public void process() {
            addLog("ServiceA 로직 수행");
        }
    }

    static class ServiceB {
        public void process() {
            addLog("ServiceB 로직 수행");
        }
    }

    static class ServiceC {
        public void process() {
            addLog("ServiceC 로직 수행");
        }
    }
}
