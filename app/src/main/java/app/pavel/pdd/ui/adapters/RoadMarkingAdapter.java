package app.pavel.pdd.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.pavel.pdd.R;
import app.pavel.pdd.data.RoadMarking;
import app.pavel.pdd.ui.LaunchActivity;
import app.pavel.pdd.utils.HandbookLiveDataRoom;

public class RoadMarkingAdapter extends
        RecyclerView.Adapter<RoadMarkingAdapter.ViewHolder>{

    public interface OnRoadMarkingClickListener {
        void onRoadMarkingClickListener(String roadMarkingTitle);
    }

    private final Context context;
    private List<RoadMarking> data;
    private final LayoutInflater layoutInflater;
    private final OnRoadMarkingClickListener onRoadMarkingClickListener;

    public RoadMarkingAdapter(Context context, OnRoadMarkingClickListener listener) {
        this.context = context;
        this.data = new ArrayList<>();
        this.onRoadMarkingClickListener = listener;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RoadMarking> newData) {
        if (data != null) {
            RoadMarkingAdapter.CategoryDiffCallback categoryDiffCallback =
                    new RoadMarkingAdapter.CategoryDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(categoryDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }
        else {
            // first initialization
            data = newData;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textViewTitle);
        }

        void bind(final RoadMarking roadMarking) {
            if (roadMarking != null) {

                String imageName = roadMarking.getImageName();

                int resID = context.getResources()
                        .getIdentifier(imageName, "drawable",
                                HandbookLiveDataRoom.getThisPackageName());

                imageView.setImageResource(resID);

                tvTitle.setText(roadMarking.getTitle());
                tvTitle.setTypeface(
                        HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
                tvTitle.setTextSize(HandbookLiveDataRoom.getTitleTextViewSize(
                        LaunchActivity.currentTextSize));

                itemView.setOnClickListener(view -> {
                    if (onRoadMarkingClickListener != null)
                        onRoadMarkingClickListener.onRoadMarkingClickListener(roadMarking.getTitle());
                });

            }
        }
    }

    class CategoryDiffCallback extends DiffUtil.Callback {

        private final List<RoadMarking> oldCategories, newCategories;

        CategoryDiffCallback(List<RoadMarking> oldCategories, List<RoadMarking> newCategories) {
            this.oldCategories = oldCategories;
            this.newCategories = newCategories;
        }

        @Override
        public int getOldListSize() {
            return oldCategories.size();
        }

        @Override
        public int getNewListSize() {
            return newCategories.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCategories.get(oldItemPosition).getTitle()
                    .equals(newCategories.get(newItemPosition).getTitle());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCategories.get(oldItemPosition).equals(newCategories.get(newItemPosition));
        }
    }

}