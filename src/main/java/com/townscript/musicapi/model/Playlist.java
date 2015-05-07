package com.townscript.musicapi.model;

public class Playlist {
	private int id;
	private int song_id;
	private int user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSongId() {
		return song_id;
	}
	public void setSongId(int song_id) {
		this.song_id = song_id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
}