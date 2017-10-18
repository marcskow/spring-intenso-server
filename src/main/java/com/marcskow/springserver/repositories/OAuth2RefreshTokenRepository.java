package com.marcskow.springserver.repositories;

import com.marcskow.springserver.security.token.OAuth2AuthenticationRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {
    OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}