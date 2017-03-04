package mydatabase;

/**
 * Created by ShaoBin on 2017/2/16.
 */
public class Tag {
	private int Id;
    private String TagName;
	private int Count;
    private int ExcerptPostId;
    private int WikiPostId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTagName() {
		return TagName;
	}
	public void setTagName(String tagName) {
		TagName = tagName;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public int getExcerptPostId() {
		return ExcerptPostId;
	}
	public void setExcerptPostId(int excerptPostId) {
		ExcerptPostId = excerptPostId;
	}
	public int getWikiPostId() {
		return WikiPostId;
	}
	public void setWikiPostId(int wikiPostId) {
		WikiPostId = wikiPostId;
	}
    
}
