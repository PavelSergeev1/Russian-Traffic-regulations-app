package app.pavel.pdd.ui;

import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {

    private int margin;

    private Rect rect;

    private Paint paint;

    MarginItemDecorator(int margin) {
        this.margin = margin;

        //paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint.setColor(Color.TRANSPARENT);
        //paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        this.rect = outRect;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(margin, margin, margin, margin);
        } else {
            outRect.set(margin, 0, margin, margin);
        }
    }

    /*

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //drawR(c, parent);
        c.drawRect(rect.left, rect.top, rect.right, rect.bottom, paint);
    }

     */
}
