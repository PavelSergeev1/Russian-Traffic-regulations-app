package app.pavel.handbooklivedataroom.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;

    public Category() {}

    @Ignore
    public Category(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // get set
}
