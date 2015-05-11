package com.townscript.musicapi.service.song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.townscript.musicapi.dao.JdbcTemplateFactory;
import com.townscript.musicapi.model.Song;
import com.townscript.musicapi.model.SongRowMapper;
import com.townscript.musicapi.service.song.SongService;
import com.townscript.musicapi.service.song.SongServiceImpl;

public class SongServiceTest {
	
	public SongServiceTest(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/townscript/musicapi/test-beans.xml");
		songService = context.getBean(SongService.class);
	}
	
	private SongService songService;
	
	
	
	public SongService getSongService() {
		return songService;
	}

	public void setSongService(SongService songService) {
		this.songService = songService;
	}

	@Before
	public void createEnvironMent()
	{
		String sqlForAllSongs = "DELETE FROM ALL_SONGS";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sqlForAllSongs);
		
		String sqlForPlaylist = "DELETE FROM PLAYLIST";
		jdbcTemplate.update(sqlForPlaylist);
		
		String sqlForUser = "DELETE FROM USERS";
		jdbcTemplate.update(sqlForUser);
		
		System.out.println("Set Up Environment");
	}
	
	@After
	public void clearEnvironMent()
	{
		String sqlForAllSongs = "DELETE FROM ALL_SONGS";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sqlForAllSongs);
		
		String sqlForPlaylist = "DELETE FROM PLAYLIST";
		 
		JdbcTemplate jdbcTemplateForPlaylist = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplateForPlaylist.update(sqlForPlaylist);
		
		String sqlForUser = "DELETE FROM USERS";
		jdbcTemplate.update(sqlForUser);
		
		System.out.println("Cleared Environment");
	}
	
	@Test
	public void testAddSong(){
		Song song = new Song();
		song.setTitle("in the end");
		song.setArtist("linkin park");
		song.setPath("/var/www/music/lp/intheend.mp3");
		int songId = songService.addSong(song);
		
		String sqlForAccess = "SELECT * FROM ALL_SONGS " +
				"WHERE SONG_ID = "+ songId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<Song> songList = jdbcTemplateForAccess.query(sqlForAccess, new SongRowMapper());
		
		Assert.assertEquals(songList.get(0).getId(), songId);
		Assert.assertEquals(songList.get(0).getTitle(), "in the end");
		Assert.assertEquals(songList.get(0).getArtist(), "linkin park");
		Assert.assertEquals(songList.get(0).getPath(), "/var/www/music/lp/intheend.mp3");
		
	}
	
	@Test
	public void testMoveToPlaylist(){
		final String sqlForUser = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplateForUser = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolderForUser = new GeneratedKeyHolder();
		jdbcTemplateForUser.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sqlForUser,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolderForUser);

		int userId  =  keyHolderForUser.getKey().intValue();
		
		final String sql = "INSERT INTO ALL_SONGS (SONG_ID, SONG_TITLE, SONG_ARTIST, THUMBS_UP_COUNT, THUMBS_DOWN_COUNT, SONG_PATH, IN_PLAYLIST) VALUES (0, 'in the end', 'linkin park', 12, 1, '/var/www/songs/lp/intheend.mp3', false)";
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int songId  =  keyHolder.getKey().intValue();
		
		songService.moveToPlaylist(songId, userId);
		
		String sqlForAccess = "SELECT * FROM ALL_SONGS " +
				"WHERE SONG_ID = "+ songId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<Song> songList = jdbcTemplateForAccess.query(sqlForAccess, new SongRowMapper());
		
		Assert.assertTrue(songList.get(0).getInPlaylist());
	}
	
	@Test
	public void testUpVote(){
		final String sql = "INSERT INTO ALL_SONGS (SONG_ID, SONG_TITLE, SONG_ARTIST, THUMBS_UP_COUNT, THUMBS_DOWN_COUNT, SONG_PATH, IN_PLAYLIST) VALUES (0, 'in the end', 'linkin park', 12, 1, '/var/www/songs/lp/intheend.mp3', false)";
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int songId  =  keyHolder.getKey().intValue();
		
		songService.upVote(songId);
		
		String sqlForAccess = "SELECT * FROM ALL_SONGS " +
				"WHERE SONG_ID = "+ songId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<Song> songList = jdbcTemplateForAccess.query(sqlForAccess, new SongRowMapper());
		
		Assert.assertEquals(songList.get(0).getThumbsUpCount(),13 );
	}
	
	@Test
	public void testDownVote(){
		final String sql = "INSERT INTO ALL_SONGS (SONG_ID, SONG_TITLE, SONG_ARTIST, THUMBS_UP_COUNT, THUMBS_DOWN_COUNT, SONG_PATH, IN_PLAYLIST) VALUES (0, 'in the end', 'linkin park', 12, 1, '/var/www/songs/lp/intheend.mp3', false)";
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int songId  =  keyHolder.getKey().intValue();
		
		songService.downVote(songId);
		
		String sqlForAccess = "SELECT * FROM ALL_SONGS " +
				"WHERE SONG_ID = "+ songId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<Song> songList = jdbcTemplateForAccess.query(sqlForAccess, new SongRowMapper());
		
		Assert.assertEquals(songList.get(0).getThumbsDownCount(),2 );
	}
}
