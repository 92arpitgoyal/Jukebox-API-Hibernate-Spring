package com.townscript.musicapi.dao.songinplaylist;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.townscript.musicapi.model.SongInPlaylist;

@Repository
public class SongInPlaylistDaoHnateImpl extends HibernateDaoSupport implements SongInPlaylistDao{

	@Override
	public int addToPlaylist(int songId, int userId) {
		SongInPlaylist songInPlaylist = new SongInPlaylist();
		
		songInPlaylist.setId(0);
		songInPlaylist.setSongId(songId);
		songInPlaylist.setUserId(userId);
		songInPlaylist.setBeingPlayed(false);
		
		getHibernateTemplate().save(songInPlaylist);
		return songInPlaylist.getId();
	}

}
