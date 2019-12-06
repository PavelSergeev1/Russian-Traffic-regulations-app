package app.pavel.pdd.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {

    private final int margin;

    public MarginItemDecorator(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(margin, margin, margin, margin);
        } else {
            outRect.set(margin, 0, margin, margin);
        }
    }

}
