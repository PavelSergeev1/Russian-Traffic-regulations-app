package app.pavel.pdd.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "main_provisions")
public class MainProvisions {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "category_id")
    private String categoryId;

    @ColumnInfo(name = "imageName")
    @Nullable
    private String imageName;

    @ColumnInfo(name = "paragraph")
    @Nullable
    private String paragraph;

    public MainProvisions() { }

    @Ignore
    public MainProvisions(String categoryId,
                           @org.jetbrains.annotations.Nullable String imageName,
                           @org.jetbrains.annotations.Nullable String paragraph) {
        this.categoryId = categoryId;
        this.imageName = imageName;
        this.paragraph = paragraph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @org.jetbrains.annotations.Nullable
    public String getImageName() {
        return imageName;
    }

    public void setImageName(@Nullable String imageName) {
        this.imageName = imageName;
    }

    @org.jetbrains.annotations.Nullable
    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(@Nullable String paragraph) {
        this.paragraph = paragraph;
    }
}
