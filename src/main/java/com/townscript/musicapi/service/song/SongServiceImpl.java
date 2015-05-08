package com.townscript.musicapi.service.song;

import com.townscript.musicapi.dao.song.SongDao;
import com.townscript.musicapi.dao.song.SongDaoImpl;
import com.townscript.musicapi.dao.songinplaylist.SongInPlaylistDao;
import com.townscript.musicapi.dao.songinplaylist.SongInPlaylistDaoImpl;
import com.townscript.musicapi.model.Song;

public class SongServiceImpl implements SongService {

	SongDao songDao = new SongDaoImpl();
	SongInPlaylistDao songInPlaylistDao = new SongInPlaylistDaoImpl();
	
	@Override
	public void moveToPlaylist(int songId, int userId) {
		songDao.addToPlaylist(songId);
		songInPlaylistDao.addToPlaylist(songId, userId);
	}

	@Override
	public void upVote(int songId) {
		songDao.upVoteSong(songId);
	}

	@Override
	public void downVote(int songId) {
		songDao.downVoteSong(songId);
	}

	@Override
	public int addSong(Song song) {
		return songDao.addToAllSongs(song);
	}

}
