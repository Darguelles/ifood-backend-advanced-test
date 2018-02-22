package com.ifood.rest;

import com.ifood.client.SpotifyClient;
import com.ifood.model.SpotifyAuthCredentials;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistController {

    @GetMapping("/holi")
    public String holi(){
        System.out.println("HOLIIIII");
        return "HOLIIII";
    }

}
