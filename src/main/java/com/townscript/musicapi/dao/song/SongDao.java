package com.townscript.musicapi.dao.song;

import com.townscript.musicapi.model.Song;

public interface SongDao {
	public int addToAllSongs(Song song); 
	public void addToPlaylist(int songId);
	public void upVoteSong(int songId);
	public void downVoteSong(int songId);
}
