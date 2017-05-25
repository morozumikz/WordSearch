package com.kz.wordsearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public int[] newPattern(final int size) {
        int ret[] = new int[size];
        for(int i=0; i<size; i++) {
            ret[i] = i;
        }

        return ret;
    }

    public int[] del(int[] arr, int value) {

        int index = 0;
        int ret[] = new int[arr.length-1];

        for(int i=0; i < arr.length; i++) {

            if(value != arr[i]) {
                ret[index] = arr[i];
                index++;
            }
        }

        return ret;
    }

    @Test
    public void testQ() {

        int r = 4;

        final int pattern[] = newPattern(r);

        int ret[] = new int[r];
        func1(ret, 0, r, pattern);
    }


    public void func1(int[] ret, final int index, final int max, final int[] pattern) {


        for(int i=0; i < max; i++) {

            int value = pattern[i];
            int nextPattern[] = del(pattern, value);
            int nextMaxSize = max - 1;

            ret[index] = value;

            if(nextMaxSize == 0){
                String str = "";
                for(int j=0; j< ret.length; j++) {
                    str += ret[j];
                }
                System.out.println(str);
                break;
            }

            func1(ret, index+1, nextMaxSize, nextPattern);
        }
    }

}