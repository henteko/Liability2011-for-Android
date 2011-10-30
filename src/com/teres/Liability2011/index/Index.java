package com.teres.Liability2011.index;

public class Index {
	private static final String TAG = Index.class.getSimpleName();
	
	private String timeOrplace;
	
	private String title;
	
	private String description;

	public Index(String timeOrplace, String title, String description) {
		super();
		this.timeOrplace = timeOrplace;
		this.title = title;
		this.description = description;
	}

	public String getTimeOrplace() {
		return timeOrplace;
	}

	public void setTimeOrplace(String timeOrplace) {
		this.timeOrplace = timeOrplace;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
