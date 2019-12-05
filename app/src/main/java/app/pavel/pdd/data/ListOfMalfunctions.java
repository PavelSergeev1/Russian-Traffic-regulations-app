package app.pavel.pdd.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_of_malfunctions")
public class ListOfMalfunctions {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "imageName")
    @Nullable
    private String imageName;

    @ColumnInfo(name = "paragraph")
    @Nullable
    private String paragraph;

    public ListOfMalfunctions() { }

    //@Ignore
    public ListOfMalfunctions(@org.jetbrains.annotations.Nullable String imageName,
                          @org.jetbrains.annotations.Nullable String paragraph) {
        this.imageName = imageName;
        this.paragraph = paragraph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
