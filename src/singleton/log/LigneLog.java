package singleton.log;

import java.util.Date;

public class LigneLog {

	private String content;
	private Date date;
	private String type;
	
	
	public LigneLog()
	{
		this.content = "";
		this.date = new Date();
		this.type = "notype";
	}
	
		
	public LigneLog(String content) {
		this();
		this.content = content;
	}
	
	public LigneLog(String content, String type)
	{
		this(content);
		this.type = type;
	}
	
	public LigneLog(String content, Date date)
	{
		this(content);
		this.date = date;
	}
	
	public LigneLog(String content, String type, Date date)
	{
		this(content, type);
		this.date = date;
	}








	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
