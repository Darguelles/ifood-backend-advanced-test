package com.ifood.repository;

import com.ifood.model.SpotifyAuthCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyAuthCredentialsRepository extends CrudRepository<SpotifyAuthCredentials, String> {



}
