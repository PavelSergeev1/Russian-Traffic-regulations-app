package app.pavel.pdd.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = TrafficSigns.class,
        parentColumns = "title",
        childColumns = "signTitle",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),
        tableName = "trafficSignsInfo",
        indices = {@Index(value = {"signTitle"} ) } )
public class TrafficSignsInfo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "signTitle")
    private String signTitle;

    @ColumnInfo(name = "imageName")
    @Nullable
    private String imageName;

    @ColumnInfo(name = "paragraph")
    @Nullable
    private String paragraph;

    public TrafficSignsInfo() { }

    @Ignore
    public TrafficSignsInfo(String signTitle,
                            @org.jetbrains.annotations.Nullable String imageName,
                            @org.jetbrains.annotations.Nullable String paragraph) {
        this.signTitle = signTitle;
        this.imageName = imageName;
        this.paragraph = paragraph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSignTitle() {
        return signTitle;
    }

    public void setSignTitle(String signTitle) {
        this.signTitle = signTitle;
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
