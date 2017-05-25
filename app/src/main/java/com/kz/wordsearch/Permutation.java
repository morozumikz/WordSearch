package com.kz.wordsearch;

public class Permutation {

    //結果取得用
    private int[][] mPatterns = null;
    private int mPatternIndex = 0;
    private void setPatterns(final int[] ret) {
        if(mPatternIndex < mPatterns.length) {
            int i=0;
            for(int value : ret) {
                mPatterns[mPatternIndex][i++] = value;
            }
            mPatternIndex++;
        }
    }

//    private ArrayList<ArrayList<Integer>> convertPatterns() {
//
//        ArrayList<ArrayList<Integer>> arrPatterns = new ArrayList<>();
//
//        for(int[] pattern : mPatterns) {
//
//            ArrayList<Integer> arrPattern = new ArrayList<>();
//
//            for (int i : pattern) {
//                arrPattern.add(i);
//            }
//
//            arrPatterns.add(arrPattern);
//        }
//
//        return arrPatterns;
//    }

//    private ArrayList<int[]> mPatterns = null;


    /**
     * 順列の総数を求める
     * @param n :
     * @param r :
     * @return : 総数
     */
    public long nPr(int n, int r){

        if((n - r) < 0) return 0;

        int last = n - r + 1;

        long ret = 1;
        for (int i=n; last <= i; i--) {
            ret *= i;
        }

        return ret;
    }

    /**
     * n = r のときの基本パターンを取得する
     * @param size : 配列のサイズ
     * @return : 基本パターン配列
     */
    private int[] newPattern(final int size) {
        int ret[] = new int[size];
        for(int i=0; i<size; i++) {
            ret[i] = i;
        }

        return ret;
    }

    /**
     * 既に使われた数字を削除する
     * @param input : 元の配列
     * @param value : 使われた文字
     * @return : 削除後の配列
     */
    private int[] removeValue(int[] input, int value) {

        int ret[] = new int[input.length-1];

        int index = 0;
        for(int current : input) {

            if(value != current) {
                ret[index] = current;
                index++;
            }
        }

        return ret;
    }

    /**
     * 順列（再帰）
     * @param ret : 結果
     * @param index : 配列のインデックス
     * @param max : パターンの最大長
     * @param pattern : パターン
     */
    private void perm(int[] ret, final int index, final int max, final int[] pattern) {

        for(int i=0; i < max; i++) {

            int value = pattern[i];
            int nextPattern[] = removeValue(pattern, value);
            int nextMaxSize = max - 1;

            ret[index] = value;

            if(nextMaxSize == 0){
                setPatterns(ret);
//                String str = "";
//                for(int j=0; j< ret.length; j++) {
//                    str += ret[j];
//                }
//                System.out.println(str);
                break;
            }

            perm(ret, index+1, nextMaxSize, nextPattern);
        }
    }

    /**
     * 順列取得
     * @param length : n = r の場合でのn
     * @return : 結果
     */
    public int[][] get(final int length) {

        long max = nPr(length, length);

        if(Integer.MAX_VALUE < max) {
            return null;
        }

        int lines = (int)max;

        if(Integer.MAX_VALUE < lines*length) {
            return null;
        }

        mPatterns = new int [lines][length];

        final int pattern[] = newPattern(length);
        int ret[] = new int[length];
        perm(ret, 0, length, pattern);

        return mPatterns;
    }

}