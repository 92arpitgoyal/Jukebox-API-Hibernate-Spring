package com.townscript.musicapi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.townscript.musicapi.model.SongInPlaylist;

public class SongInPlaylistRowMapper implements RowMapper<SongInPlaylist> {

	@Override
	public SongInPlaylist mapRow(ResultSet rs, int rowNum) throws SQLException {
		SongInPlaylist playlist = new SongInPlaylist();
		playlist.setId(rs.getInt("PLAY_ID"));
		playlist.setSongId(rs.getInt("SONG_ID"));
		playlist.setUserId(rs.getInt("USER_ID"));	
		
		return playlist;
	}

}