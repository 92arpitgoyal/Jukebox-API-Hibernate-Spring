package com.townscript.musicapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
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
	private int thumbsUpCount;

	@Column(name = "THUMBS_DOWN_COUNT")
	private int thumbsDownCount;

	@Column(name = "SONG_PATH")
	private String path;

	@Column(name = "IN_PLAYLIST")
	private boolean inPlaylist;
	
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
		return thumbsUpCount;
	}
	public void setThumbsUpCount(int thumbsupcount) {
		this.thumbsUpCount = thumbsupcount;
	}
	public int getThumbsDownCount() {
		return thumbsDownCount;
	}
	public void setThumbsDownCount(int thumbsdowncount) {
		this.thumbsDownCount = thumbsdowncount;
	}
	public String getPath(){
		return path;
	}
	public void setPath(String path){
		this.path = path;
	}
	public boolean getInPlaylist(){
		return inPlaylist;
	}
	public void setInPlaylist(boolean inplaylist){
		this.inPlaylist = inplaylist;
	}
	
}