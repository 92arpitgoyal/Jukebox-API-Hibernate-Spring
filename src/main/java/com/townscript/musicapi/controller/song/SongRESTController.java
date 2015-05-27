package com.townscript.musicapi.controller.song;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.musicapi.model.Song;
import com.townscript.musicapi.model.User;
import com.townscript.musicapi.service.song.SongService;
import com.townscript.musicapi.service.user.UserService;

@RestController
@RequestMapping(value = "/song")
public class SongRESTController {

	public SongRESTController() {
		super();
        if (songService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/musicapi/main-beans.xml");
                songService = (SongService) context
                                .getBean("SongServiceImpl");
        }
	}
	
	private SongService songService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getall", method=RequestMethod.GET)
	public List<Song> accessAllSongs(){
		return songService.readSongs();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public Song accessSong(@RequestParam(value="id",required=true) int id){
		return songService.readSong(id);
	}
}
