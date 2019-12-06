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
import app.pavel.pdd.data.Launch;
import app.pavel.pdd.ui.LaunchActivity;
import app.pavel.pdd.utils.HandbookLiveDataRoom;

public class LaunchAdapter extends
        RecyclerView.Adapter<LaunchAdapter.ViewHolder> {

    public interface OnLaunchItemClickListener {
        void onItemClickListener(String itemTitle);
    }

    private final Context context;
    private List<Launch> data;
    private final LayoutInflater layoutInflater;
    private final OnLaunchItemClickListener onLaunchItemClickListener;

    public LaunchAdapter(Context context, OnLaunchItemClickListener listener) {
        this.context = context;
        this.data = new ArrayList<>();
        this.onLaunchItemClickListener = listener;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.launch_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textViewTitle, textViewDescription;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewLaunch);
            textViewTitle = itemView.findViewById(R.id.textViewTitleLaunch);
            textViewDescription = itemView.findViewById(R.id.textViewContentLaunch);
        }

        void bind(final Launch launch) {
            if (launch != null) {

                String imageName = launch.getImageName();

                int resID = context.getResources()
                        .getIdentifier(imageName, "drawable",
                                HandbookLiveDataRoom.getThisPackageName());

                imageView.setImageResource(resID);

                textViewTitle.setText(launch.getTitle());
                textViewTitle.setTextSize(HandbookLiveDataRoom.getTitleTextViewSize(
                        LaunchActivity.currentTextSize));
                textViewTitle.setTypeface(
                        HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));

                if (!launch.getDescription().equals("no")) {
                    textViewDescription.setText(launch.getDescription());
                    textViewDescription.setTypeface(
                            HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
                    textViewDescription.setTextSize(HandbookLiveDataRoom
                            .getTextViewSize(LaunchActivity.currentTextSize));
                }

                itemView.setOnClickListener(view -> {
                    if (onLaunchItemClickListener != null)
                        onLaunchItemClickListener.onItemClickListener(launch.getTitle());
                });

            }
        }
    }

    public void setLaunchData(List<Launch> newData) {
        if (data != null) {
            LaunchDiffCallback launchDiffCallback = new LaunchDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(launchDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }
        else {
            // first initialization
            data = newData;
        }
    }

    class LaunchDiffCallback extends DiffUtil.Callback {

        private final List<Launch> oldCategories, newCategories;

        LaunchDiffCallback(List<Launch> oldCategories, List<Launch> newCategories) {
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

            return oldCategories.get(oldItemPosition)
                    .equals(newCategories.get(newItemPosition));
        }
    }

}