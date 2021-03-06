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
import app.pavel.pdd.data.TrafficRules;
import app.pavel.pdd.ui.LaunchActivity;
import app.pavel.pdd.utils.HandbookLiveDataRoom;

public class TrafficRulesAdapter extends
        RecyclerView.Adapter<TrafficRulesAdapter.ViewHolder>{

    public interface OnRuleClickListener {
        void onRuleClickListener(String ruleTitle);
    }

    private final Context context;
    private List<TrafficRules> data;
    private final LayoutInflater layoutInflater;
    private final OnRuleClickListener onRuleClickListener;

    public TrafficRulesAdapter(Context context, OnRuleClickListener listener) {
        this.context = context;
        this.data = new ArrayList<>();
        this.onRuleClickListener = listener;
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

    public void setData(List<TrafficRules> newData) {
        if (data != null) {
            CategoryDiffCallback categoryDiffCallback = new CategoryDiffCallback(data, newData);
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

        void bind(final TrafficRules trafficRules) {
            if (trafficRules != null) {

                String imageName = trafficRules.getImageName();

                int resID = context.getResources()
                        .getIdentifier(imageName, "drawable",
                                HandbookLiveDataRoom.getThisPackageName());

                imageView.setImageResource(resID);

                tvTitle.setText(trafficRules.getTitle());
                tvTitle.setTypeface(
                        HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
                tvTitle.setTextSize(HandbookLiveDataRoom.getTitleTextViewSize(
                        LaunchActivity.currentTextSize));

                itemView.setOnClickListener(view -> {
                    if (onRuleClickListener != null)
                        onRuleClickListener.onRuleClickListener(trafficRules.getTitle());
                });

            }
        }
    }

    class CategoryDiffCallback extends DiffUtil.Callback {

        private final List<TrafficRules> oldCategories, newCategories;

        CategoryDiffCallback(List<TrafficRules> oldCategories, List<TrafficRules> newCategories) {
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