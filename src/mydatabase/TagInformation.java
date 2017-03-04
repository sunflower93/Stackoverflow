package mydatabase;

/**
 * Created by ShaoBin on 2017/2/16.
 */
public class TagInformation {
	private int Id;
    private String CreationDate;
    private String Body;
    private int OwnerUserId;
    private String OwnerDisplayName;
    private int LastEditorUserId;
    private String LastEditorDisplayName;
    private String LastEditDate;
    private String LastActivityDate;
    private String ClosedDate;
    private String Title;
    private String CommunityOwnedDate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
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
	public void setOwnerDisplayName(String ownerDisplayName) {
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
	public String getCommunityOwnedDate() {
		return CommunityOwnedDate;
	}
	public void setCommunityOwnedDate(String communityOwnedDate) {
		CommunityOwnedDate = communityOwnedDate;
	}
	@Override
	public String toString() {
		return "mydatabase.TagInformation [Id=" + Id + ", CreationDate=" + CreationDate + ", Body=" + Body + ", OwnerUserId="
				+ OwnerUserId + ", OwnerDisplayName=" + OwnerDisplayName + ", LastEditorUserId=" + LastEditorUserId
				+ ", LastEditorDisplayName=" + LastEditorDisplayName + ", LastEditDate=" + LastEditDate
				+ ", LastActivityDate=" + LastActivityDate + ", ClosedDate=" + ClosedDate + ", Title=" + Title
				+ ", CommunityOwnedDate=" + CommunityOwnedDate + "]";
	}
	
}
