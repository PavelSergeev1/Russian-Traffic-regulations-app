package app.pavel.handbooklivedataroom.ui;

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

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class CategoriesAdapter extends
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    public interface OnCategoryClickListener {
        void onCategoryClickListener(String categotyTitle);
    }

    private List<Category> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnCategoryClickListener onCategoryClickListener;

    public CategoriesAdapter(Context context, OnCategoryClickListener listener) {
        this.data = new ArrayList<>();
        this.context = context;
        this.onCategoryClickListener = listener;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.layout_category_item, parent, false);

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

    public void setData(List<Category> newData) {
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
        private ImageView imageView;
        private TextView tvTitle, tvContent;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }

        void bind(final Category category) {
            if (category != null) {

                String imageName = category.getImageName();
                int resID = HandbookLiveDataRoom.setImageInImageView(imageView, imageName);
                imageView.setImageResource(resID);

                tvTitle.setText(category.getTitle());
                tvContent.setText(category.getContent());

                itemView.setOnClickListener(view -> {
                    if (onCategoryClickListener != null)
                        onCategoryClickListener.onCategoryClickListener(category.getTitle());
                });

            }
        }
    }

    class CategoryDiffCallback extends DiffUtil.Callback {

        private final List<Category> oldCategories, newCategories;

        public CategoryDiffCallback(List<Category> oldCategories, List<Category> newCategories) {
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
            // return oldCategories.get(oldItemPosition).getId() == newCategories.get(newItemPosition).getId();
            return oldCategories.get(oldItemPosition).getTitle() == newCategories.get(newItemPosition).getTitle();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCategories.get(oldItemPosition).equals(newCategories.get(newItemPosition));
        }
    }

}