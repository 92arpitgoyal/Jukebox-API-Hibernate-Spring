package com.townscript.musicapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALL_SONGS")
public class Song {
	@Id @GeneratedValue
	@Column(name = "SONG_ID")
	private int id;
	
	@Column(name = "SONG_TITLE")
	private String title;
	
	@Column(name = "SONG_ARTIST")
	private String artist;
	
	@Column(name = "THUMBS_UP_COUNT")
	private int thumbsupcount;

	@Column(name = "THUMBS_DOWN_COUNT")
	private int thumbsdowncount;

	@Column(name = "SONG_PATH")
	private String path;

	@Column(name = "IN_PLAYLIST")
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