package com.townscript.musicapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
@Table(name = "PLAYLIST")
public class SongInPlaylist {

	@Id @GeneratedValue
	@Column(name = "PLAYLIST_ID")
	private int id;

	@Column(name = "SONG_ID")
	private int songId;

	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "BEING_PLAYED")
	private boolean beingPlayed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean  getBeingPlayed(){
		return beingPlayed;
	}
	public void setBeingPlayed(boolean beingPlayed){
		this.beingPlayed = beingPlayed;
	}
	
}