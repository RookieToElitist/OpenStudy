package com.study.demo3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class test {

    public static void main(String[] args) {
//        ArrayList<String> cacheMsg=new ArrayList<>();
//        int count=0;
//        while (true){
//            cacheMsg.add("123");
//
//            System.out.println("++count="+ ++count);
//            System.out.println("count="+ count);
//            if (count==5){
//
//                break;
//            }
//        }
//
//        for(String s:cacheMsg){
//            System.out.println(s);
//        }

        TreeSet<Long> set = new TreeSet<Long>();

        set.add(1L);//0
        set.add(7l);//1
        set.add(8l);//2
        set.add(10l);//3
        set.add(15l);//4
        set.headSet(4l).clear();//清除值小于4的数据。
        Iterator<Long> iterator1 = set.iterator();

        while (iterator1.hasNext()) {
            long l = iterator1.next().longValue();
            System.out.println(l);
        }

        System.out.println("----------------------------------------");

        // creating TreeSet
        TreeSet <Integer>tree = new TreeSet<Integer>();
        TreeSet <Integer>treeheadset = new TreeSet<Integer>();

        // adding in the tree
        tree.add(12);
        tree.add(13);
        tree.add(14);
        tree.add(15);
        tree.add(16);
        tree.add(17);

        // getting values less than 15
        treeheadset = (TreeSet)tree.headSet(15);

        // creating iterator
        Iterator iterator;
        iterator = treeheadset.iterator();

        //Displaying the tree set data
        System.out.println("Tree set data: ");

        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
    }
}

