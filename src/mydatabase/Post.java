package mydatabase;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class Post {
	private int Id;
	private int PostTypeId;
    private int ParentId;
    private int AcceptedAnswerId;
    private String CreationDate;
    private int Score;
    private int ViewCount;
    private String Body;
    private int OwnerUserId;
	private String OwnerDisplayName;
    private int LastEditorUserId;
    private String LastEditorDisplayName;
    private String LastEditDate;
    private String LastActivityDate;
    private String ClosedDate;
    private String Title;
    private String Tags;
    private int AnswerCount;
    private int CommentCount;
    private int FavoriteCount;
    private String CommunityOwnedDate;
    public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPostTypeId() {
		return PostTypeId;
	}
	public void setPostTypeId(int postTypeId) {
		PostTypeId = postTypeId;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentID) {
		ParentId = parentID;
	}
	public int getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}
	public void setAcceptedAnswerId(int acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getViewCount() {
		return ViewCount;
	}
	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public int getOwnerUserId() {
		return OwnerUserId;
	}
	public void setOwnerUserId(int ownerUserId) {
		OwnerUserId = ownerUserId;
	}
	public String getOwnerDisplayName() {
		return OwnerDisplayName;
	}
	public void OwnerDisplayName(String ownerDisplayName) {
		OwnerDisplayName = ownerDisplayName;
	}
	public int getLastEditorUserId() {
		return LastEditorUserId;
	}
	public void setLastEditorUserId(int lastEditorUserId) {
		LastEditorUserId = lastEditorUserId;
	}
	public String getLastEditorDisplayName() {
		return LastEditorDisplayName;
	}
	public void setLastEditorDisplayName(String lastEditorDisplayName) {
		LastEditorDisplayName = lastEditorDisplayName;
	}
	public String getLastEditDate() {
		return LastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		LastEditDate = lastEditDate;
	}
	public String getLastActivityDate() {
		return LastActivityDate;
	}
	public void setLastActivityDate(String lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}
	public String getClosedDate() {
		return ClosedDate;
	}
	public void setClosedDate(String closedDate) {
		ClosedDate = closedDate;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public int getAnswerCount() {
		return AnswerCount;
	}
	public void setAnswerCount(int answerCount) {
		AnswerCount = answerCount;
	}
	public int getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(int commentCount) {
		CommentCount = commentCount;
	}
	public int getFavoriteCount() {
		return FavoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		FavoriteCount = favoriteCount;
	}
	public String getCommunityOwnedDate() {
		return CommunityOwnedDate;
	}
	public void setCommunityOwnedDate(String communityOwnedDate) {
		CommunityOwnedDate = communityOwnedDate;
	}
}
