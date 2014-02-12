package com.kr.syntax;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 12.02.14
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class RemeberJavaOOP {
    static class A {
        public A(String a) {
            System.out.println("in A constructor");
        }

        public A() {
            System.out.println("in A default constructor");
        }
    }

    static class B extends A {
        public B() {
            super("aa");
            System.out.println("in B");
        }

        public B(String aa) {
            super();
        }
    }

    static class C {
        A doSomething(B a) {
            System.out.println("In C");
            return new A();
        }
    }

    static class D extends C {
        @Override
        public B doSomething(B b) {
            return new B("zz");
        }

        void doZ() {
        }

        void doZ(int i) {
            System.out.println("int");
        }

        void doZ(Integer i) {
            System.out.println("Integer");
        }
    }

    public static void main(String[] args) {
        new B();
//        new D().doSomething();
//        B z = new D().doSomething();
//        new D().doZ(3.134);
    }
}
