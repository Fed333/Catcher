package com.example.catcher.algorithms;

import com.example.catcher.domain.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditorialDistance {
    private Map<Pair<String, String>, Integer> hashDistances = new HashMap<>();
    private String source;
    private String collate;

    public EditorialDistance(String s, String c) throws OverwhelmedAmountOfMemoryException{
        if (s.length()*c.length() > OverwhelmedAmountOfMemoryException.forbiddenCapacity){
            throw new OverwhelmedAmountOfMemoryException(s.length(), c.length());
        }
        source = s;
        collate = c;
    }


    //Пошук відстанні Левентшейна алгоритмом Вагнера-Фішера
    private int levenshtein(){
        int n = source.length();
        int m = collate.length();

        if (n == 0 || m == 0){
            //якшо один рядок порожній к-ть операцій рівна к-ть видалень чи вставок
            return Math.max(n, m);
        }

        //1. Проініціалізувати двовимірний масив початковими значенням
        int[][] d = new int[n+1][m+1];

        //Заповнити верхній рядок
        d[0][0] = source.charAt(0) == collate.charAt(0)?0:1;
        for(int j = 1; j < m; ++j){
            d[0][j] = source.charAt(0) == collate.charAt(j)?d[0][j-1]:d[0][j-1]+1;
        }
        //Заповнити  лівий стовпчик
        for(int i = 1; i < n; ++i){
            d[i][0] = source.charAt(i) == collate.charAt(0)?d[i-1][0]:d[i-1][0]+1;
        }

        //2. Вирахувати редакційні відстанні для всіх підпослідовностей рядків source і collate

        for(int j = 1; j < m; ++j){
            for(int i = 1; i < n; ++i){
                //якщо символи співпадають
                if (source.charAt(i) == collate.charAt(j)){
                    //значення редакційної відстанні не змінюється, беремо попередні
                    d[i][j] = d[i-1][j-1];
                }
                else {
                    //беремо краще з трьох попередніх варіантів плюс одна, поточна операція
                    d[i][j] = 1 + min(
                            d[i-1][j],  //видалення символа
                            d[i][j-1],  //вставка  символа
                            d[i-1][j-1] //заміна символа
                    );
                }
            }
        }

        //3. Повернути кінцевий результат, що знаходиться в d[n-1][m-1]
        return d[n-1][m-1];
    }

    private void printLog(String operation, long operationTime){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        System.out.printf(
                "[Дата]: %20s | [Операція]: %25s | [Значення]: {'%15s', '%15s'} | [Час]: %8d нс\n",
                dateFormat.format(date), operation, source, collate, operationTime
        );
    }

    //обраховує редакційну відстань
    public int dist(){
        Pair<String, String> key = new Pair<>(source, collate);
        //якщо значення присутнє в хеш таблиці
        if (hashDistances.containsKey(key)){
            //дістаємо його за O(1) - в кращому, O(n) - в гіршому
            return hashDistances.get(key);
        }
        //якщо ж його там немає
        else{
            //вираховуємо його за O(n*m) алгоритмом Вагнера Фішера
            int res = levenshtein();
            //додаємо в хеш-таблицю
            hashDistances.put(key, res);
            return res;
        }
    }

    //схожість від 0 до 1. (0 - зовсім не схоже, 1 - ідентичність)
    public double similarity(){
        int bigger = Math.max(source.length(), collate.length());
        return  ((double)bigger - dist())/bigger;
    }
    private int min(int... a){
        int m = a[0];
        for (int i = 1; i < a.length; i++){
            if (m > a[i])
                m = a[i];
        }
        return m;
    }

    public static class OverwhelmedAmountOfMemoryException extends Exception {
        public static final Integer forbiddenCapacity = 1000000;
        public OverwhelmedAmountOfMemoryException(int length1, int length2) {
            super(
                    "Для рядків розмірами " + length1 + " та " + length2 +
                    " алгоритм Вагнера-Фішера не може бути застосовним\n" +
                    "Розмір масиву " + length1*length2 + " перевищує допустиму " + forbiddenCapacity
            );

        }
    }

    public Map<Pair<String, String>, Integer> getHashDistances() {
        return hashDistances;
    }

    public void setHashDistances(Map<Pair<String, String>, Integer> hashDistances) {
        this.hashDistances = hashDistances;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source != null) {
            this.source = source;
        }
    }

    public String getCollate() {
        return collate;
    }

    public void setCollate(String collate) {
        if (collate != null) {
            this.collate = collate;
        }
    }
}

