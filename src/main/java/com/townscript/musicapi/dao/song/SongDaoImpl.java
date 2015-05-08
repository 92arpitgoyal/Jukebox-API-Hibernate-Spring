package com.townscript.musicapi.dao.song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.townscript.musicapi.dao.JdbcTemplateFactory;
import com.townscript.musicapi.model.Song;

public class SongDaoImpl implements SongDao {

	@Override
	public void addToPlaylist(int songId) {
		String sql = "UPDATE ALL_SONGS SET IN_PLAYLIST = true " +
				"WHERE SONG_ID = ?";
		Object[] params = { songId };
		int[] types = {Types.BIGINT};
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void upVoteSong(int songId) {
		String sql = "UPDATE ALL_SONGS SET THUMBS_UP_COUNT = THUMBS_UP_COUNT + 1 " +
				"WHERE SONG_ID = ?";
		Object[] params = { songId };
		int[] types = {Types.BIGINT};
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);

	}

	@Override
	public void downVoteSong(int songId) {
		String sql = "UPDATE ALL_SONGS SET THUMBS_DOWN_COUNT = THUMBS_DOWN_COUNT + 1 " +
				"WHERE SONG_ID = ?";
		Object[] params = { songId };
		int[] types = {Types.BIGINT};
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
		
	}

	@Override
	public int addToAllSongs(Song song) {
		final String sql = "INSERT INTO ALL_SONGS (SONG_ID, SONG_TITLE, SONG_ARTIST, THUMBS_UP_COUNT, THUMBS_DOWN_COUNT, SONG_PATH, IN_PLAYLIST) VALUES (0, '"+ song.getTitle() +"', '"+ song.getArtist() +"', 0, 0, '"+ song.getPath() +"', false)";
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		return keyHolder.getKey().intValue();
	}

}
