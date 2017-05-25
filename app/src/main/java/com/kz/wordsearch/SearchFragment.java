package com.kz.wordsearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchFragment extends Fragment {

    /**
     * This interfaces must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onSearchFragmentInteraction(final String input);
    }
    private OnFragmentInteractionListener mListener;


    public SearchFragment() {

    }

    public static SearchFragment newInstance(final int id) {
        return new SearchFragment();
    }

    public static final int MAX_WORD_LENGTH = 9;
    private EditText mEdit;
    private TextView mAllCount;
    private ListView mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        mEdit = (EditText) view.findViewById(R.id.f_search_edit_multi);
        mAllCount = (TextView)  view.findViewById(R.id.f_search_text_max);
        mList = (ListView) view.findViewById(R.id.f_search_list);

        view.findViewById(R.id.f_search_button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //キーボードを隠す
                hideKeyboard(view);
                //データ処理はMainActivityで扱うので処理を戻す
                mListener.onSearchFragmentInteraction(mEdit.getText().toString());
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * キーボードを隠す
     * @param view :
     */
    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    /**
     * リストを更新する
     * @param words : リスト
     */
    public void updateList(final String[] words) {

        mAllCount.setText("全" + words.length + "件です");

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, words);
        mList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void updateMessage(final String msg) {
        mAllCount.setText(msg);
    }

}
