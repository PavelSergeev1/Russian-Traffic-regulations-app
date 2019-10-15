package app.pavel.handbooklivedataroom.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
    parentColumns = "title",
    childColumns = "category_id",
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE),
        tableName = "categoryInfo",
        indices = {@Index(value = {"category_id"}, unique = false)})
public class CategoryInfo {

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

    public CategoryInfo() { }

    @Ignore
    public CategoryInfo(String categoryId, String imageName, String paragraph) {
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(@Nullable String imageName) {
        this.imageName = imageName;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(@Nullable String paragraph) {
        this.paragraph = paragraph;
    }

}
