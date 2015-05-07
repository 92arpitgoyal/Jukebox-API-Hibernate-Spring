package com.townscript.musicapi.model;

public class AllSongs {
	private int id;
	private String title;
	private String artist;
	private int thumbsupcount;
	private int thumbsdowncount;
	private String path;
	private boolean inplaylist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getThumbsUpCount() {
		return thumbsupcount;
	}
	public void setThumbsUpCount(int thumbsupcount) {
		this.thumbsupcount = thumbsupcount;
	}
	public int getThumbsDownCount() {
		return thumbsdowncount;
	}
	public void setThumbsDownCount(int thumbsdowncount) {
		this.thumbsdowncount = thumbsdowncount;
	}
	public String getPath(){
		return path;
	}
	public void setPath(String path){
		this.path = path;
	}
	public boolean getInPlaylist(){
		return inplaylist;
	}
	public void setInPlaylist(boolean inplaylist){
		this.inplaylist = inplaylist;
	}
	
}