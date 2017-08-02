package com.fueled.draggablerecyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Reference @link {https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf}
 *
 * @author chetansachdeva on 26/07/17
 */

public class DragItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

	public static final float ALPHA_FULL = 1.0f;
	private boolean dragEnabled;
	private OnItemDragListener onItemDragListener;

	private DragItemTouchHelperCallback(int dragDirs, int swipeDirs) {
		super(dragDirs, swipeDirs);
	}

	private DragItemTouchHelperCallback(Builder builder) {
		this(builder.dragDirs, builder.swipeDirs);
		dragEnabled = builder.dragEnabled;
		onItemDragListener = builder.onItemDragListener;
	}

	@Override public boolean isLongPressDragEnabled() {
		return dragEnabled;
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
		if (source.getItemViewType() != target.getItemViewType()) {
			return false;
		}
		// Notify the adapter of the move
		onItemDragListener.onItemDragged(source.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	@Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

	}

	@Override public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
		// We only want the active item to change
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			viewHolder.itemView.setAlpha(ALPHA_FULL / 2);
			viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
		}
		super.onSelectedChanged(viewHolder, actionState);
	}

	@Override public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		viewHolder.itemView.setAlpha(ALPHA_FULL);
		viewHolder.itemView.setBackgroundColor(0);
		super.clearView(recyclerView, viewHolder);
	}

	public interface OnItemDragListener {
		void onItemDragged(int indexFrom, int indexTo);
	}

	public static final class Builder {
		private int dragDirs, swipeDirs;
		private OnItemDragListener onItemDragListener;
		private boolean dragEnabled;

		public Builder(int dragDirs, int swipeDirs) {
			this.dragDirs = dragDirs;
			this.swipeDirs = swipeDirs;
		}

		public Builder onItemDragListener(OnItemDragListener onItemDragListener) {
			this.onItemDragListener = onItemDragListener;
			return this;
		}

		public Builder setDragEnabled(boolean dragEnabled) {
			this.dragEnabled = dragEnabled;
			return this;
		}

		public DragItemTouchHelperCallback build() {
			return new DragItemTouchHelperCallback(this);
		}
	}
}
