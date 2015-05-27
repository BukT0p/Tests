package com.dataart.audiotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ITEMS_NUMBER = 100000;
    private TextView results;
    private Button arraylistTest;
    private Button vectorTest;
    private Button linkedlistTest;
    private static String[] variations = new String[]{"sad", "qweqw", "cvsdsa", "123qwd", "lksf", "saidhoqkai", "vgkjhnsdkjdf", "dsad", "sfdsfs", "32874u9"};//10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        results = (TextView) findViewById(R.id.results);
        arraylistTest = (Button) findViewById(R.id.arraylist_test);
        vectorTest = (Button) findViewById(R.id.vector_test);
        linkedlistTest = (Button) findViewById(R.id.linkedlist_test);

        arraylistTest.setOnClickListener(this);
        vectorTest.setOnClickListener(this);
        linkedlistTest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        long startPoint = System.currentTimeMillis();
        if (v == arraylistTest) {
            ArrayList<String> generated = generateArrayList();
            results.append("\n ArrayList generated in " + (System.currentTimeMillis() - startPoint) + " milliseconds");
            startPoint = System.currentTimeMillis();
            testSimpleFor(generated);
            long diff = System.currentTimeMillis() - startPoint;
            results.append("\nArrayList | SimpleFor = " + diff);
            startPoint = System.currentTimeMillis();
            testForEach(generated);
            diff = System.currentTimeMillis() - startPoint;
            results.append("\nArrayList | ForEach = " + diff);
        } else if (v == vectorTest) {
            Vector<String> generated = generateVector();
            results.append("\n Vector generated in " + (System.currentTimeMillis() - startPoint) + " milliseconds");
            startPoint = System.currentTimeMillis();
            testSimpleFor(generated);
            long diff = System.currentTimeMillis() - startPoint;
            results.append("\nVector | SimpleFor = " + diff);
            startPoint = System.currentTimeMillis();
            testForEach(generated);
            diff = System.currentTimeMillis() - startPoint;
            results.append("\nVector | ForEach = " + diff);
        } else if (v == linkedlistTest) {
            LinkedList<String> generated = generateLinkedList();
            results.append("\n LinkedList generated in " + (System.currentTimeMillis() - startPoint) + " milliseconds");
            startPoint = System.currentTimeMillis();
            testSimpleFor(generated);
            long diff = System.currentTimeMillis() - startPoint;
            results.append("\nLinkedList | SimpleFor = " + diff);
            startPoint = System.currentTimeMillis();
            testForEach(generated);
            diff = System.currentTimeMillis() - startPoint;
            results.append("\nLinkedList | ForEach = " + diff);
        }
    }

    public int testForEach(List<String> generated) {
        int allCharsCounter = 0;
        for (String item : generated) {
            allCharsCounter += item.length();
        }
        return allCharsCounter;
    }

    public int testSimpleFor(List<String> generated) {
        int size = generated.size();
        int allCharsCounter = 0;
        for (int i = 0; i < size; i++) {
            allCharsCounter += generated.get(i).length();
        }
        return allCharsCounter;
    }

    public ArrayList<String> generateArrayList() {
        Random random = new Random();
        ArrayList<String> result = new ArrayList<>(ITEMS_NUMBER);
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            result.add(variations[random.nextInt(10)]);
        }
        return result;
    }

    public Vector<String> generateVector() {
        Random random = new Random();
        Vector<String> result = new Vector<>(ITEMS_NUMBER);
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            result.add(variations[random.nextInt(10)]);
        }
        return result;
    }

    public LinkedList<String> generateLinkedList() {
        Random random = new Random();
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            result.add(variations[random.nextInt(10)]);
        }
        return result;
    }
}
