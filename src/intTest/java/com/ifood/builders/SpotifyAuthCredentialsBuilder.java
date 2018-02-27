package com.ifood.builders;

import com.ifood.model.SpotifyAuthCredentials;

import java.util.function.Consumer;

public class SpotifyAuthCredentialsBuilder {

    public final static String DEFAULT_ID = "112233";
    public final static String DEFAULT_ACCESS_TOKEN = "abcde123456";
    public final static String DEFAULT_TOKEN_TYPE = "Bearer";
    public final static Long DEFAULT_EXPIRES_IN = 3600L;
    public final static String DEFAULT_SCOPE = "";

    public String id = DEFAULT_ID;
    public String accessToken = DEFAULT_ACCESS_TOKEN;
    public String tokenType = DEFAULT_TOKEN_TYPE;
    public Long expiresIn = DEFAULT_EXPIRES_IN;
    public String scope = DEFAULT_SCOPE;

    private SpotifyAuthCredentialsBuilder() {
    }

    public static SpotifyAuthCredentialsBuilder build() {
        return new SpotifyAuthCredentialsBuilder();
    }

    public SpotifyAuthCredentials now(){
        return new SpotifyAuthCredentials(accessToken, tokenType, expiresIn, scope);
    }

    public SpotifyAuthCredentialsBuilder with(Consumer<SpotifyAuthCredentialsBuilder> s) {
        s.accept(this);
        return this;
    }

}
