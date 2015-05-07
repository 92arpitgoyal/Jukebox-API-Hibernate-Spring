package com.townscript.musicapi.dao.playlist;

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

public class PlaylistDaoImpl implements PlaylistDao {

	@Override
	public int addToPlaylist(int songId, int userId) {
		final String sql = "INSERT INTO PLAYLIST" +
				"(PLAY_ID, SONG_ID, USER_ID, BEING_PLAYED) VALUES ("+ 0 +", '"+ songId+"', '"+userId+"', false)";
		//Object[] params = { 0, songId, userId, false };
		//int[] types = {Types.BIGINT, Types.BIGINT, Types.BIGINT, Types.BIT};
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,   Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		return keyHolder.getKey().intValue();
	}

}
