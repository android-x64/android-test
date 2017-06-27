package com.test.admin.testproj.tests.quick_algorithm_count_unique_cards;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.test.admin.testproj.R;

import java.util.ArrayList;
import java.util.Collection;

public class TestActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.btn_test_algorithm).setOnClickListener(v -> {
            testAlgorithm3_The_Best();
            testAlgorithm4();
        });
    }


    private void testAlgorithm3_The_Best() {
        Collection<Card> cards = createRandomCards(100000);
        Log.e("WWW", "WWW beginning 3 ************************");
        int unique = Card.countUniqueCards_1(cards);
        Log.e("WWW", "WWW unique= " + unique);
        Log.e("WWW", "WWW end 3");
    }

    private void testAlgorithm4() {
        Collection<Card> cards = createRandomCards(100000);
        Log.e("WWW", "WWW beginning 4 ************************");
        int unique = Card.countUniqueCards_2(cards);
        Log.e("WWW", "WWW unique= " + unique);
        Log.e("WWW", "WWW end 4");
    }

    private Collection<Card> createRandomCards(int number) {
        System.gc();
        Runtime.getRuntime().gc();
        Collection<Card> cards = new ArrayList<>();
        byte code1 = -128;
        byte code2 = 127;
        byte code3 = 63;
        for (int i = 0; i < number; i++) {
            Card card = new Card();
            int factor = i < number / 2 ? i : i - number / 2;
            card.name = "SSS ";
            card.code = new byte[3];
            card.code[0] = code1++;
            card.code[1] = code2--;
            card.code[2] = code3++;
            card.address = "a " + i;
            cards.add(card);

            if (code1 == 127) {
                code1 = -128;
            }
            if (code2 == -128) {
                code2 = 127;
            }
            if (code3 == 127) {
                code3 = 127 / 2;
            }
        }
        return cards;
    }
}
