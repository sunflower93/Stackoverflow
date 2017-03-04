package mydatabase;

/**
 * Created by ShaoBin on 2017/2/16.
 */
public class Comment {
	private int Id;
	private int PostId;
    private int Score;
    private String Text;
    private String CreationDate;
    private int UserId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPostId() {
		return PostId;
	}
	public void setPostId(int postId) {
		PostId = postId;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	@Override
	public String toString() {
		return "mydatabase.Comment [Id=" + Id + ", PostId=" + PostId + ", Score=" + Score + ", Text=" + Text + ", CreationDate="
				+ CreationDate + ", UserId=" + UserId + "]";
	}
    
}
