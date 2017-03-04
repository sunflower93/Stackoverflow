package mydatabase;

/**
 * Created by ShaoBin on 2017/2/16.
 */
public class User {
	private int Id;
	private int	Reputation;
	private String CreationDate;
	private String DisplayName;
	private String EmailHash;
	private String LastAccessDate;
	private String WebsiteUrl;
	private String Location;
	private String AboutMe;
	private int Views;
	private int UpVotes;
	private int DownVotes;
	private String ProfileImageUrl;
	private int Age ;
	private int AccountId;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getReputation() {
		return Reputation;
	}

	public void setReputation(int reputation) {
		Reputation = reputation;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public String getDisplayName() {
		return DisplayName;
	}

	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}

	public String getEmailHash() {
		return EmailHash;
	}

	public void setEmailHash(String emailHash) {
		EmailHash = emailHash;
	}

	public String getLastAccessDate() {
		return LastAccessDate;
	}

	public void setLastAccessDate(String lastAccessDate) {
		LastAccessDate = lastAccessDate;
	}

	public String getWebsiteUrl() {
		return WebsiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		WebsiteUrl = websiteUrl;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getAboutMe() {
		return AboutMe;
	}

	public void setAboutMe(String aboutMe) {
		AboutMe = aboutMe;
	}

	public int getViews() {
		return Views;
	}

	public void setViews(int views) {
		Views = views;
	}

	public int getUpVotes() {
		return UpVotes;
	}

	public void setUpVotes(int upVotes) {
		UpVotes = upVotes;
	}

	public int getDownVotes() {
		return DownVotes;
	}

	public void setDownVotes(int downVotes) {
		DownVotes = downVotes;
	}

	public String getProfileImageUrl() {
		return ProfileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		ProfileImageUrl = profileImageUrl;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getAccountId() {
		return AccountId;
	}

	public void setAccountId(int accountId) {
		AccountId = accountId;
	}

	@Override
	public String toString() {
		return "mydatabase.User [Id=" + Id + ", Reputation=" + Reputation + ", CreationDate=" + CreationDate + ", DisplayName="
				+ DisplayName + ", EmailHash=" + EmailHash + ", LastAccessDate=" + LastAccessDate + ", WebsiteUrl="
				+ WebsiteUrl + ", Location=" + Location + ", AboutMe=" + AboutMe + ", Views=" + Views + ", UpVotes="
				+ UpVotes + ", DownVotes=" + DownVotes + ", ProfileImageUrl=" + ProfileImageUrl + ", Age=" + Age
				+ ", AccountId=" + AccountId + "]";
	}

 
}
