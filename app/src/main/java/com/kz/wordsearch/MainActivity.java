package com.kz.wordsearch;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //画面固定（縦）
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        replaceFragment();
    }

    /**
     * メインのフラグメントを取得する
     */
    private Fragment getFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.main_container);
    }

    /**
     * フラグメントの切り替え（擬似ページ遷移）
     */
    private void replaceFragment() {

        Fragment f = SearchFragment.newInstance(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, f).commit();
    }

    /**
     * フラグメントからの呼び出し
     * @param input : 入力された文字列
     */
    @Override
    public void onSearchFragmentInteraction(final String input) {

        SearchFragment f = (SearchFragment) getFragment();

        //文字の長さ
        final int length = input.length();

        if(SearchFragment.MAX_WORD_LENGTH < length) {
            f.updateMessage("文字列が長すぎます");
            return;
        }

        //文字列の長さに対応する数値の順列を取得
        //{0,1,2,3},{0,1,3,2},...
        Permutation perm = new Permutation();
        final int[][] arrResult = perm.get(length);

        if(arrResult == null) {
            f.updateMessage("件数が多すぎます");
            return;
        }

        ArrayList<String> words = new ArrayList<>();

        //数値の配列に対応させて入力された文字列から文字を取得し単語を作成
        for(int[] arr : arrResult) {

            String word = "";
            for(int i : arr) {
                word += input.charAt(i);
            }

            words.add(word);
        }

        //フラグメント側に通知
        String[] param = words.toArray(new String[0]);
        f.updateList(param);
    }

}
