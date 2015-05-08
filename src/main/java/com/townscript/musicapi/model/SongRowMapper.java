package com.townscript.musicapi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.townscript.musicapi.model.Song;

public class SongRowMapper implements RowMapper<Song> {

	@Override
	public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
		Song allsongs = new Song();
		allsongs.setId(rs.getInt("SONG_ID"));
		allsongs.setTitle(rs.getString("SONG_TITLE"));
		allsongs.setArtist(rs.getString("SONG_ARTIST"));
		allsongs.setThumbsUpCount(rs.getInt("THUMBS_UP_COUNT"));
		allsongs.setThumbsDownCount(rs.getInt("THUMBS_DOWN_COUNT"));
		allsongs.setPath(rs.getString("SONG_PATH"));	
		allsongs.setInPlaylist(rs.getBoolean("IN_PLAYLIST"));	
		
		return allsongs;
	}

}