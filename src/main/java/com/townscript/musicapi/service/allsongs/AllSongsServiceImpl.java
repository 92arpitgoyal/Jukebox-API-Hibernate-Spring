package com.townscript.musicapi.service.allsongs;

import com.townscript.musicapi.dao.allsongs.AllSongsDao;
import com.townscript.musicapi.dao.allsongs.AllSongsDaoImpl;
import com.townscript.musicapi.dao.playlist.PlaylistDao;
import com.townscript.musicapi.dao.playlist.PlaylistDaoImpl;
import com.townscript.musicapi.model.AllSongs;

public class AllSongsServiceImpl implements AllSongsService {

	AllSongsDao allSongsDao = new AllSongsDaoImpl();
	PlaylistDao playlistDao = new PlaylistDaoImpl();
	
	@Override
	public void moveToPlaylist(int songId, int userId) {
		allSongsDao.addToPlaylist(songId);
		playlistDao.addToPlaylist(songId, userId);
	}

	@Override
	public void upVote(int songId) {
		allSongsDao.upVoteSong(songId);
	}

	@Override
	public void downVote(int songId) {
		allSongsDao.downVoteSong(songId);
	}

	@Override
	public int addSong(String title, String artist, String path) {
		return allSongsDao.addToAllSongs(title, artist, path);
	}

}
