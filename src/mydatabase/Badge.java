package mydatabase;

/**
 * Created by ShaoBin on 2017/3/6.
 */
public class Badge {
    private int Id;
    private int UserId;
    private String Name;
    private String Date;
    private int Classtype;
    private boolean TagBased;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getUserId() {
        return UserId;
    }
    public void setUserId(int userid) {
        UserId = userid;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public int getClasstype() {
        return Classtype;
    }
    public void setClasstype(int classtype) {
        Classtype = classtype;
    }
    public boolean getTagBased() {
        return TagBased;
    }
    public void setTagBased(boolean tagBased) {
        TagBased = tagBased;
    }

}
