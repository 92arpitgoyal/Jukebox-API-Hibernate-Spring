package com.townscript.musicapi;

import com.townscript.musicapi.dao.song.SongDao;
import com.townscript.musicapi.dao.song.SongDaoHnateImpl;
import com.townscript.musicapi.dao.user.UserDao;
import com.townscript.musicapi.dao.user.UserDaoImpl;
import com.townscript.musicapi.model.Song;
import com.townscript.musicapi.model.User;

public class Test {
	public static void main(String[] args){
		/*User user = new User();
		user.setName("shyam Singh");
		user.setEmail("shyamsingh@ts.com");
		user.setPassword("qwerty1234");
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);*/
		Song song = new Song();
		song.setTitle("dil chahta hain");
		song.setArtist("amir khan");
		song.setPath("/var/www/html/bollywood/");
		SongDao songDao = new SongDaoHnateImpl();
		songDao.addToAllSongs(song);
		
	}
}
