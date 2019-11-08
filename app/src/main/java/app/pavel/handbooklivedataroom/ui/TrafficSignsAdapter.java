package app.pavel.handbooklivedataroom.ui;

import android.content.Context;
import android.os.AsyncTask;
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

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.TrafficSigns;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class TrafficSignsAdapter extends
        RecyclerView.Adapter<TrafficSignsAdapter.ViewHolder> {

    public interface OnSignClickListener {
        void onSignClickListener(String signTitle);
    }

    private String imageName;

    private Context context;
    private List<TrafficSigns> data;
    private LayoutInflater layoutInflater;
    private OnSignClickListener onSignClickListener;

    TrafficSignsAdapter(Context context, OnSignClickListener listener) {
        this.data = new ArrayList<>();
        this.context = context;
        this.onSignClickListener = listener;
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textViewTitle);
        }

        void bind(final TrafficSigns trafficSigns) {
            if (trafficSigns != null) {

                imageName = trafficSigns.getImageName();

                int resID = HandbookLiveDataRoom.getContext().getResources()
                        .getIdentifier( imageName, "drawable",
                                HandbookLiveDataRoom.getThisPackageName());

                imageView.setImageResource(resID);

                //new SetImageInImageView().execute(imageView);

                tvTitle.setText(trafficSigns.getTitle());

                itemView.setOnClickListener(view -> {
                    if (onSignClickListener != null)
                        onSignClickListener.onSignClickListener(trafficSigns.getTitle());
                });

            }
        }
    }

    public void setData(List<TrafficSigns> newData) {
        if (data != null) {
            CategoryDiffCallBack categoryDiffCallBack = new CategoryDiffCallBack(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(categoryDiffCallBack);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }
        else {
            // first initialization
            data = newData;
        }
    }

    class CategoryDiffCallBack extends DiffUtil.Callback {

        private final List<TrafficSigns> oldSigns, newSigns;

        CategoryDiffCallBack(List<TrafficSigns> oldSigns, List<TrafficSigns> newSigns) {
            this.oldSigns = oldSigns;
            this.newSigns = newSigns;
        }

        @Override
        public int getOldListSize() {
            return oldSigns.size();
        }

        @Override
        public int getNewListSize() {
            return newSigns.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

            return oldSigns.get(oldItemPosition).getTitle()
                    .equals(newSigns.get(newItemPosition).getTitle());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

            return oldSigns.get(oldItemPosition).equals(newSigns.get(newItemPosition));
        }
    }

    public class SetImageInImageView extends AsyncTask<ImageView, Void, Integer> {
        ImageView imageView = null;
        @Override
        protected Integer doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return getImageFromResource(imageName);
        }

        @Override
        protected void onPostExecute(Integer resID) {

            imageView.setImageResource(resID);
        }

        private int getImageFromResource(String imageName) {
            return HandbookLiveDataRoom.getResourceId(imageName);
        }
    }
}