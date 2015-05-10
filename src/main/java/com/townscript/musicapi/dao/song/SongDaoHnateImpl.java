package com.townscript.musicapi.dao.song;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.musicapi.model.Song;

public class SongDaoHnateImpl extends HibernateDaoSupport implements SongDao {
	@Override
	public int addToAllSongs(Song song) {
		getHibernateTemplate().save(song);
		return song.getId();
	}

	@Override
	public void addToPlaylist(int songId) {
		String hql = "UPDATE" + Song.class.getName() + "set inplaylist = :inplaylist "  + 
	             "WHERE songId = :songid";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("inplaylist", true);
		query.setParameter("songid", songId);
		query.executeUpdate();
	}

	@Override
	public void upVoteSong(int songId) {
		String hql = "UPDATE" + Song.class.getName() + "set upVoteSong = upVoteSong + 1 "  + 
	             "WHERE songId = :songid";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("songid", songId);
		query.executeUpdate();
	}

	@Override
	public void downVoteSong(int songId) {
		String hql = "UPDATE" + Song.class.getName() + "set downVoteSong = downVoteSong + 1 "  + 
	             "WHERE songId = :songid";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("songid", songId);
		query.executeUpdate();
		
	}

}
