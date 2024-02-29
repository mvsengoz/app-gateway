package com.gateway.app;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HackerRankTest {


    @Test
    public void flatMapTest() throws CloneNotSupportedException, InterruptedException {

        System.out.println("****Map*****");

        Map k = new HashMap();
        k.put("1", "volkan1");
        k.put("2", "volkan1");
        k.put("3", null);


        k.forEach((key, value) -> {
            System.out.println(key + "--" + value);
        });

        System.out.println("****Set*****");

        Set s = new HashSet();
        s.add("1");
        s.add("1");
        s.add(null);

        s.forEach(se -> {
            System.out.println(se);
        });

        System.out.println("****Qeue*****");

        Queue<String> q = new LinkedList<>();
        q.add("1");
        q.add("2");
        q.add("3");

        var v = q.poll();
        System.out.println(v);

        v = q.poll();
        System.out.println(v);

        v = q.poll();
        System.out.println(v);


        System.out.println("****Stack*****");

        Stack<String> ss = new Stack<>();
        ss.add("1");
        ss.add("2");
        ss.add("3");

        v = ss.pop();
        System.out.println(v);

        v = ss.pop();
        System.out.println(v);

        v = ss.pop();
        System.out.println(v);

        System.out.println("****List*****");
        List<Book> books = new ArrayList<Book>();
        var book = new Book("anna karanina", 1000);
        books.add(book);
        book = new Book("les miserables", 1001);
        books.add(book);
        book = new Book("before adem", 1010);
        books.add(book);

        books.forEach(bb -> System.out.println(bb.toString()));
        books.sort((o1, o2) -> o1.pageCount - o2.pageCount);
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.pageCount - o2.pageCount;
            }
        });

    }
    @Test
    void oddNumber() {
        List<Integer> testData = List.of(1,2,3,4,5,6,7,8,9,10);
        var oddNumbers = testData.stream().filter(f-> (f%2 != 0) & f>5).collect(Collectors.toList());
        oddNumbers.stream().forEach(p->System.out.println(p));

    }

    @Test
    public void recursiveSearchFile() throws IOException {
        /*grep file*/
        String directory = "C:\\" ;

        listFiles(new File(directory));

    }

    @Test
    void counWordsInSentence(){
        String tmp = "hello mello hello Mello how are you ?" ;
        List<String> tmpList = Arrays.asList(tmp.split(" "));

        Map<String, Long> result = tmpList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        result.forEach((v,k)->{
            System.out.println(v+" - "+k);
        });

    }

    @Test
    void countOccurenceInList(){

        List<Integer> tmpList = List.of(1,1,1,2,3,4,5,6);

        Map<Integer, Long> result = tmpList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        result.forEach((v,k)->{
            System.out.println(v+" - "+k);
        });

    }




    /************private methods*************/
    public void recursiveFibo() throws IOException {

        /*Fibo*/
        for (int i = 0; i < 19; i++) {
            System.out.println(fibo(i));
        }

    }

    private File listFiles(File file) {

        if (file.isDirectory()) {
            List<File> fileList = Arrays.asList(file.listFiles());
            fileList.stream().forEach(f -> {
                if (Files.isReadable(f.toPath())) {
                    listFiles(f);
                }
            });

        } else {
            System.out.println(file.getPath());
        }

        return null;
    }

    private int fibo(int n) {

        //1, 1, 2, 3, 5, 8, 13, 21, 34, 55,

        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }

    }


    public class Book {
        public String name;

        public int pageCount;
        public Book(String name, int pageCount) {
            this.name = name;
            this.pageCount = pageCount;
        }
    }

}
