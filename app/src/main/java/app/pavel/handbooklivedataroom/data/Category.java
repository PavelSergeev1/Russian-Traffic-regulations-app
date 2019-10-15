package app.pavel.handbooklivedataroom.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

    /**

    @PrimaryKey(autoGenerate = true)
    private int id;

     */

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "imageName")
    private String imageName;

    public Category() { }

    @Ignore
    public Category(String title, String content, String imageName) {
        this.title = title;
        this.content = content;
        this.imageName = imageName;
    }

    /**

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
