package com.example.service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestClass {

    public static void main(String[] args) {

        //int i=()->5;

        //(String s) -> System.out.print(s);

        //String s=() -> "哈哈哈哈";
        //System.out.println( );

        // 类型声明
        //MathOperation addition = (int a, int b) -> a + b;

        TestClass testClass=new TestClass();

        MathOperation plus=(a,b) -> a+b;

        testClass.oper(1,2,plus);

        plus=(a,b) -> a-b;

        testClass.oper(1,2,plus);

        plus=(a,b) ->5;

        testClass.oper(1,2,plus);

        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();


        new Thread(()->System.out.println("1112222333")).start();

        List<String> list= Arrays.asList("t","t1","t2","t3","t4","t5","t5","t111");
        //list.forEach(n->System.out.println(n));
        list.forEach(System.out::println);

        System.out.println("-----------------------------------------");

        testClass.test(list,(str)-> str.endsWith("3"));

        Predicate<String> p=n->n.startsWith("t");

        Predicate<String> p2=n->n.indexOf("3")>-1;

        testClass.test(list,p.and(p2));

        list.stream().map(s -> s+"s").forEach(System.out::println);

        System.out.println("===================================");

        System.out.println(list.stream().mapToInt(s -> s.length()).reduce((sum,s)->sum+s).getAsInt());


        //list.stream().map(s -> s+"s").collect(Collections.toList);

        list.stream().map(s -> s+"ss").collect(Collectors.toList()).forEach(System.out::println);

        System.out.println(list.stream().map(s -> s+"ss").collect(Collectors.joining(",")));;

        list.stream().map(s -> s+"ss").distinct().forEach(System.out::println);

        IntSummaryStatistics summary=list.stream().mapToInt(s -> s.length()).summaryStatistics();

        System.out.println("max="+summary.getMax()+",min="+summary.getMin()+",avg="+summary.getAverage()+",count="+summary.getCount()+",sum="+summary.getSum());

        List<Integer> intList=Arrays.asList(1,2,3,4,5);

        int i=100;

        //intList.forEach(n->{ System.out.println(n*i);System.out.println(n*i); });

        int sum=0;
        for(i=0;i<=200;i++)
        {
            sum+=i;
        }

        System.out.println(sum);

        Map<String,String> map=new HashMap<String,String>();

    }

    interface MathOperation {
        int operation(int a, int b);
    }

    private void oper(int a,int b,MathOperation plus)
    {
        System.out.println(plus.operation(a,b));
    }

    private void test(List<String> list, Predicate<String> condition)
    {
        for (String s:list)
        {
            if(condition.test(s))
            {
                System.out.println(s);
            }
        }
    }

}
