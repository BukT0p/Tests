package com.dataart.audiotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Byte Code for simple for:
 * <p/>
 * aload_1
 * invokeinterface java/util/List/size()I 1
 * istore_2
 * iconst_0
 * istore_3
 * iconst_0
 * istore 4
 * iload 4
 * iload_2
 * if_icmpge 21
 * iload_3
 * aload_1
 * iload 4
 * invokeinterface java/util/List/get(I)Ljava/lang/Object; 2
 * checkcast java/lang/String
 * invokevirtual java/lang/String/length()I
 * iadd
 * istore_3
 * iinc 4 1
 * goto 8
 * iload_3
 * ireturn
 * <p/>
 * Byte Code for ForEach:
 * iconst_0
 * istore_2
 * aload_1
 * invokeinterface java/util/List/iterator()Ljava/util/Iterator; 1
 * astore_3
 * aload_3
 * invokeinterface java/util/Iterator/hasNext()Z 1
 * ifeq 17
 * iload_2
 * aload_3
 * invokeinterface java/util/Iterator/next()Ljava/lang/Object; 1
 * checkcast java/lang/String
 * invokevirtual java/lang/String/length()I
 * iadd
 * istore_2
 * goto 6
 * iload_2
 * ireturn
 */
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
        if (v == arraylistTest) {
            startTest(generateArrayList());
        } else if (v == vectorTest) {
            startTest(generateVector());
        } else if (v == linkedlistTest) {
            startTest(generateLinkedList());
        }
    }

    void startTest(List<String> generated) {
        long startPoint = System.currentTimeMillis();
        String tag = generated.getClass().getSimpleName();
        results.append(tag + " | generated in " + (System.currentTimeMillis() - startPoint) + " milliseconds\n");
        startPoint = System.currentTimeMillis();
        testSimpleFor(generated);
        long diff = System.currentTimeMillis() - startPoint;
        results.append(tag + " | SimpleFor = " + diff + "\n");
        startPoint = System.currentTimeMillis();
        testForEach(generated);
        diff = System.currentTimeMillis() - startPoint;
        results.append(tag + " | ForEach = " + diff + "\n");
    }

    int testForEach(List<String> generated) {
        int allCharsCounter = 0;
        for (String item : generated) {
            allCharsCounter += item.length();
        }
        return allCharsCounter;
    }

    int testSimpleFor(List<String> generated) {
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

    Vector<String> generateVector() {
        Random random = new Random();
        Vector<String> result = new Vector<>(ITEMS_NUMBER);
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            result.add(variations[random.nextInt(10)]);
        }
        return result;
    }

    LinkedList<String> generateLinkedList() {
        Random random = new Random();
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < ITEMS_NUMBER; i++) {
            result.add(variations[random.nextInt(10)]);
        }
        return result;
    }
}
