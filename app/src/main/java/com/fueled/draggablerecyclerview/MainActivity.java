package com.fueled.draggablerecyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.fueled.draggablerecyclerview.adapter.RVAdapter;
import com.fueled.draggablerecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragHandler {

	private RVAdapter adapter;
	private ActivityMainBinding binding;
	private List<String> list = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.setHandler(this);

		setRecyclerView();
	}

	private void setRecyclerView() {
		binding.rv.setLayoutManager(new LinearLayoutManager(this));
		adapter = new RVAdapter(getList());
		binding.rv.setAdapter(adapter);
	}

	public List<String> getList() {
		for (int i = 0; i < 5; i++) {
			list.add("Drag " + i);
		}
		return list;
	}

	@Override public void onItemDragged(int indexFrom, int indexTo) {
		adapter.move(indexFrom, indexTo);
	}
}
