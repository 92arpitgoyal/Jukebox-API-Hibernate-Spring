package com.townscript.musicapi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.townscript.musicapi.model.Playlist;

public class PlaylistRowMapper implements RowMapper<Playlist> {

	@Override
	public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
		Playlist playlist = new Playlist();
		playlist.setId(rs.getInt("PLAY_ID"));
		playlist.setSongId(rs.getInt("SONG_ID"));
		playlist.setUserId(rs.getInt("USER_ID"));	
		
		return playlist;
	}

}