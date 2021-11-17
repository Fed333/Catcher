package com.example.catcher.algorithms;


import java.util.Objects;

public class Pair<R, V> implements Comparable<Pair<R, V>>{
    private R first;
    private V second;

    Pair(R f, V s){
        first = f;
        second = s;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) &&
                second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Pair<R, V> p){
        int comp = compareFirst(p.getFirst());
        if (comp == 0) comp = compareSecond(p.getSecond());
        return comp;
    }

    private int compareFirst(R f) throws ClassCastException{
        if (f instanceof Comparable){
            return ((Comparable<R>) f).compareTo((R)first);
        }
        else throw new ClassCastException();
    }

    private int compareSecond(V s) throws ClassCastException{
        if (s instanceof Comparable){
            return ((Comparable<V>) s).compareTo((V) second);
        }
        else throw new ClassCastException();
    }

    public R getFirst() {
        return first;
    }

    public void setFirst(R first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}
