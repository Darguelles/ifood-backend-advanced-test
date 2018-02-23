package com.ifood.rest;

import com.ifood.model.Track;
import com.ifood.model.WeatherPlaylist;
import com.ifood.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PlaylistController {

    @Autowired
    MusicService musicService;

    @GetMapping("/spoti")
    public WeatherPlaylist getCred() throws Exception {
        return musicService.retrievePlaylist("Lima");
    }

}
