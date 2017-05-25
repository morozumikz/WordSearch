//package com.kz.wordsearch;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jp.justive.vecloud.R;
//import jp.justive.vecloud.list.interfaces.Cell;
//
//public class ListAdapter extends ArrayAdapter<Cell> {
//
//	//    LayoutInflater mInflater;
////    private ImageLoader mImageLoader;
//	private ArrayList<Cell> mListCells;
//
////    Context self;
//
//	public ListAdapter(Context context, List<Cell> list) {
//		super(context, 0);
////        self = context;
////        mInflater = LayoutInflater.from(context);
////        mImageLoader = new ImageLoader(context.getApplicationContext());
//		this.mListCells = new ArrayList<>(list);
//	}
//
//	@Override
//	public View getView(int position, View view, ViewGroup parent) {
//		if (view == null) {
//			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_cell, null);
////            view = mInflater.inflate(R.layout.layout_list_item, parent, false);
//		}
//
//		Cell cell = getItem(position);
//
//		if(cell == null) {
//			return view;
//		}
//
//		TextView textView = (TextView) view.findViewById(R.id.list_cell_text);
//		textView.setText(cell.getTitle());
//
//		ImageView imageView = (ImageView) view.findViewById(R.id.list_cell_image);
//
//		if(cell.getBitmap() != null) {
//			imageView.setImageBitmap(cell.getBitmap());
//		}
//		else if(cell.getResource() != 0) {
//			imageView.setImageResource(cell.getResource());
//		}
//		else if(cell.getUri() != null) {
//			imageView.setImageURI(cell.getUri());
//		}
//
//		return view;
//	}
//
//}
