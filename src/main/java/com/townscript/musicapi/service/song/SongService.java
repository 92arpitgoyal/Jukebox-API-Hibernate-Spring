package com.townscript.musicapi.service.song;

import java.util.List;

import com.townscript.musicapi.model.Song;

public interface SongService {
	public List<Song> readSongs();
	public Song readSong(int songId);
	public int addSong(Song song);
	public void moveToPlaylist(int songId, int userId);
	public void upVote(int songId);
	public void downVote(int songId);
}
