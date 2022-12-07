package de.dvdgeisler.iot.dirigera.client.api.http;

import de.dvdgeisler.iot.dirigera.client.api.model.music.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;

@Component
public class ClientMusicApi extends AbstractClientApi {
    private final static Logger log = LoggerFactory.getLogger(ClientMusicApi.class);

    public ClientMusicApi(
            final GatewayDiscovery gatewayDiscovery,
            final TokenStore tokenStore) throws SSLException {
        super(gatewayDiscovery, "music/", tokenStore);
    }

    public Mono<Music> music() {
        return this.webClient
                .get()
                .uri(UriBuilder::build)
                .headers(this.tokenStore::setBearerAuth)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, this::onError)
                .bodyToMono(Music.class);
    }
}
