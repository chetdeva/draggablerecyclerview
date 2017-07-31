package com.fueled.draggablerecyclerview.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fueled.draggablerecyclerview.R;
import com.fueled.draggablerecyclerview.databinding.ItemMainBinding;

import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 04/06/17
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH> {

	private List<String> list;

	public RVAdapter(List<String> list) {
		this.list = list;
	}

	@Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
	}

	@Override public void onBindViewHolder(VH holder, int position) {
		holder.binding.tv.setText(list.get(position));
	}

	@Override public int getItemCount() {
		return list.size();
	}

	public void move(int indexFrom, int indexTo) {
		if (indexFrom < indexTo) {
			for (int i = indexFrom; i < indexTo; i++) {
				Collections.swap(list, i, i + 1);
			}
		} else {
			for (int i = indexFrom; i > indexTo; i--) {
				Collections.swap(list, i, i - 1);
			}
		}
		notifyItemMoved(indexFrom, indexTo);
	}

	static class VH extends RecyclerView.ViewHolder {
		ItemMainBinding binding;

		public VH(View itemView) {
			super(itemView);
			binding = DataBindingUtil.bind(itemView);
		}
	}
}
