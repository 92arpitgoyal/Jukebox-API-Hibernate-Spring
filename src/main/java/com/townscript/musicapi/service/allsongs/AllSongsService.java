package com.townscript.musicapi.service.allsongs;

import com.townscript.musicapi.model.AllSongs;

public interface AllSongsService {
	public int addSong(String title, String artist, String path);
	public void moveToPlaylist(int songId, int userId);
	public void upVote(int songId);
	public void downVote(int songId);
}
