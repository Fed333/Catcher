package com.example.catcher.algorithms;

import com.example.catcher.domain.Word;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sorts {

    public static <T extends Comparable<T>> List<T> qSort(List<T> l){
        return qSort(l, T::compareTo);

    }
    public static <T>List<T> qSort(List<T> l, Comparator<T> cmp){
        if (l.size() < 2){
            return l;
        }

        List<T> smaller = new LinkedList<>();
        List<T> bigger = new LinkedList<>();
        Iterator<T> it = l.iterator();
        T pivot = it.next();
        while(it.hasNext()){
            T el = it.next();
            if (cmp.compare(el, pivot) > 0){   //el > pivot
                bigger.add(el);
            }
            else{
                smaller.add(el);
            }
        }
        smaller = qSort(smaller, cmp);
        bigger = qSort(bigger, cmp);
        smaller.add(pivot);
        smaller.addAll(bigger);
        return smaller;
    }

}
