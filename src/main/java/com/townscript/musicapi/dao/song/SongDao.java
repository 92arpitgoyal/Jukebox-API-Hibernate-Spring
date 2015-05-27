package com.townscript.musicapi.dao.song;

import java.util.List;

import com.townscript.musicapi.model.Song;

public interface SongDao {
	public List<Song> loadAllSongs();
	public Song loadSong(int songId);
	public int addToAllSongs(Song song); 
	public void addToPlaylist(int songId);
	public void upVoteSong(int songId);
	public void downVoteSong(int songId);
}
