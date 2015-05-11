package com.townscript.musicapi.service.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.musicapi.dao.song.SongDao;
//import com.townscript.musicapi.dao.song.SongDaoHnateImpl;
//import com.townscript.musicapi.dao.song.SongDaoImpl;
import com.townscript.musicapi.dao.songinplaylist.SongInPlaylistDao;
//import com.townscript.musicapi.dao.songinplaylist.SongInPlaylistDaoHnateImpl;
//import com.townscript.musicapi.dao.songinplaylist.SongInPlaylistDaoImpl;
import com.townscript.musicapi.model.Song;

@Service
@Transactional(readOnly = true)
public class SongServiceImpl implements SongService {

	//private SongDao songDao = new SongDaoImpl();
	//private SongInPlaylistDao songInPlaylistDao = new SongInPlaylistDaoImpl();
	
	@Autowired
	private SongDao songDao;
	
	
	@Autowired
	private SongInPlaylistDao songInPlaylistDao;
	
	
	
	public SongDao getSongDao() {
		return songDao;
	}

	public void setSongDao(SongDao songDao) {
		this.songDao = songDao;
	}

	public SongInPlaylistDao getSongInPlaylistDao() {
		return songInPlaylistDao;
	}

	public void setSongInPlaylistDao(SongInPlaylistDao songInPlaylistDao) {
		this.songInPlaylistDao = songInPlaylistDao;
	}

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
