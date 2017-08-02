package com.fueled.draggablerecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.fueled.draggablerecyclerview.DragItemTouchHelperCallback;

/**
 * @author chetansachdeva on 27/07/17
 */

public class BindingAdapter {

	/**
	 * Bind ItemTouchHelper.SimpleCallback with RecyclerView
	 *
	 * @param recyclerView      RecyclerView to bind to DragItemTouchHelperCallback
	 * @param dragEnabled       enable/disable drag
	 * @param onItemDrag        OnItemDragListener for dragged
	 */
	@android.databinding.BindingAdapter(value = {"dragEnabled", "onItemDrag"}, requireAll = false)
	public static void setItemDragToRecyclerView(RecyclerView recyclerView, boolean dragEnabled,
	                                             DragItemTouchHelperCallback.OnItemDragListener onItemDrag) {

		ItemTouchHelper.Callback dragCallback = new DragItemTouchHelperCallback
				.Builder(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0)
				.setDragEnabled(dragEnabled)
				.onItemDragListener(onItemDrag)
				.build();

		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(dragCallback);
		itemTouchHelper.attachToRecyclerView(recyclerView);
	}
}
