package com.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainApplication {

    public static class lock{
        Queue<String> queue = new LinkedList<>();
        String current="";
        public lock(){

        }
        public synchronized void add(String name){
            queue.add(name);
        }
        public synchronized String getKey(){
            if(current.equals("")){
                if(queue.peek() != null){
                    current = queue.poll();
                }
            }
            return current;
        }

        public boolean done(String name){
            if(current.equals(name)){
                current = "";

                return true;
            } else {
                return false;
            }
        }


    }

    public static class count{
        private int c;
        public count(){
            c=0;
        }
        public  void add(){
            c++;
        }

        public  void minus(){
            c--;
        }
        public  int getC(){
            return c;
        }


    }

    public static class semaphore{
        int flags = 0;
        public semaphore(){
            this.flags = 1;
        }

        public int getFlags() {
            return flags;
        }

        public synchronized boolean getResource(){
            if(flags>0){
                flags--;
                return true;
            } else {
                return false;
            }

        }
        public void returnResource(){
            flags++;
        }
    }

    public static class Livelock{
        int flagsA = 0;
        int flagsB = 0;
        public Livelock(){
            this.flagsA = 1;
            this.flagsB = 1;
        }

        public int getFlagsA() {
            return flagsA;
        }
        public int getFlagsB() {
            return flagsB;
        }

        public synchronized boolean getResourceA(){
            if(flagsA>0){
                flagsA--;
                return true;
            } else {
                return false;
            }

        }
        public synchronized boolean getResourceB(){
            if(flagsB>0){
                flagsB--;
                return true;
            } else {
                return false;
            }

        }
        public void returnResourceA(){
            flagsA++;
        }
        public void returnResourceB(){
            flagsB++;
        }
    }

    private static lock lock = new lock();
    private static count count = new count();
    private static semaphore semaphore = new semaphore();
    private static Livelock livelock = new Livelock();




    public static class ThreadA extends Thread{
        /**
         * normal condition
         */
//        @Override
//        public void run(){
//            while(true) {
//                while (true) {
//                    try {
//                        System.out.println("ThreadA awaits; lock:" + lock.getKey());
//                        Thread.sleep(1000);
//                        if (lock.getKey().equals("ThreadA")) {
//                            break;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("ThreadA got the Key!");
//
//                for(int i=0;i<3;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadA print i = " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                lock.done("ThreadA");
//
//            }
//        }
        /**
         * live lock
         */
//        @Override
//        public void run(){
//            String flag ="";
//            while(true) {
//
//                while (true) {
//
//                    try {
//                        System.out.println("----ThreadA awaits; lockA:" + livelock.getFlagsA() + " lockB:" + livelock.getFlagsB());
//                        Thread.sleep(1000);
//                        System.out.println("----ThreadA ask for A;");
//                        if (livelock.getResourceA()==true) {
//                            if (flag.equals("A")) {
//                                //livelock.returnResourceA();
//                            } else {
//                                livelock.returnResourceB();
//                            }
//                            flag="";
//                            flag = "A";
//                            break;
//                        } else {
//                            System.out.println("----ThreadA awaits; lockA:" + livelock.getFlagsA() + " lockB:" + livelock.getFlagsB());
//                            Thread.sleep(1000);
//                            System.out.println("----ThreadA ask for B;");
//                            if (livelock.getResourceB()==true && livelock.getFlagsA()>0) {
//
//                                if (flag.equals("A")) {
//                                    livelock.returnResourceA();
//                                } else {
//                                    //livelock.returnResourceB();
//                                }
//                                break;
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                for(int i=0;i<3;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadA print i = " + i);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//        }
        /**
         * Semaphore
         */
//        @Override
//        public void run(){
//            while(true) {
//                while (true) {
//                    try {
//                        System.out.println("----ThreadA awaits; flags:" + semaphore.getFlags());
//                        Thread.sleep(1000);
//                        if(semaphore.getResource()==true){
//                            break;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("ThreadA got the Key!");
//
//                for(int i=0;i<3;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadA print i = " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                semaphore.returnResource();
//            }
//        }
        /**
         * add
         */
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(1000);
                    count.add();
                    System.out.println("A: count" + count.getC());
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static class ThreadB extends Thread{
        /**
         * normal condition
         */
//        @Override
//        public void run(){
//            while(true) {
//                while (true) {
//                    try {
//                        System.out.println("ThreadB awaits; lock:" + lock.getKey());
//                        Thread.sleep(1000);
//                        if (lock.getKey().equals("ThreadB")) {
//                            break;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("ThreadB got the Key!");
//                for(int i=0;i<5;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadB print i = " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                lock.done("ThreadB");
//
//            }
//        }

        /**
         * live lock
         */
//        @Override
//        public void run(){
//            String flag = "";
//            while(true) {
//
//                while (true) {
//                    try {
//                        System.out.println("----ThreadB awaits; lockA:" + livelock.getFlagsA() + " lockB:" + livelock.getFlagsB());
//                        Thread.sleep(1000);
//                        System.out.println("----ThreadB ask for B;");
//                        if (livelock.getResourceB()==true) {
//                            if (flag.equals("A")) {
//                                livelock.returnResourceA();
//                            } else {
//                                //livelock.returnResourceB();
//                            }
//                            flag="B";
//                            break;
//                        } else {
//                            System.out.println("----ThreadB awaits; lockA:" + livelock.getFlagsA() + " lockB:" + livelock.getFlagsB());
//                            Thread.sleep(1000);
//                            System.out.println("----ThreadB ask for A;");
//                            if (livelock.getResourceA()==true) {
//                                if (flag.equals("A")) {
//                                    //livelock.returnResourceA();
//                                } else {
//                                    livelock.returnResourceB();
//                                }
//                                flag="A";
//                                break;
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                for(int i=0;i<3;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadB print i = " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
        /**
         * Semaphore
         */
//        @Override
//        public void run(){
//            while(true) {
//                while (true) {
//                    try {
//                        System.out.println("----ThreadB awaits; flags:" + semaphore.getFlags());
//                        Thread.sleep(1000);
//                        if(semaphore.getResource()==true){
//                            break;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("ThreadB got the Key!");
//
//                for(int i=0;i<3;i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("ThreadB print i = " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                semaphore.returnResource();
//            }
//        }
        boolean isBreak = true;
        /**
         * add
         */
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(1000);
                    count.minus();
                    System.out.println("B: count" + count.getC());
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }


    }



    public static void main(String[] args){
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        lock.add("ThreadA");
        lock.add("ThreadB");
        lock.add("ThreadA");
        lock.add("ThreadB");


        a.start();
        b.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        b.interrupt();
    }
}
