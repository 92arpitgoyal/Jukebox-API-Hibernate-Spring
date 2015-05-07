package com.townscript.musicapi.dao.allsongs;

import com.townscript.musicapi.model.AllSongs;

public interface AllSongsDao {
	public int addToAllSongs(String title, String artist, String path); 
	public void addToPlaylist(int songId);
	public void upVoteSong(int songId);
	public void downVoteSong(int songId);
}
