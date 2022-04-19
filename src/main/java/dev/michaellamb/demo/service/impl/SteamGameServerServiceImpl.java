package dev.michaellamb.demo.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.michaellamb.demo.model.external.SteamGameServerStatusResponse;
import dev.michaellamb.demo.service.SteamGameServerService;

@Service
public class SteamGameServerServiceImpl implements SteamGameServerService {
    private static final String STEAM_GAME_SERVER_SERVICE_URL = "https://api.steampowered.com/IGameServersService/GetServerList/v1/";

    private static final Logger LOG = LoggerFactory.getLogger(SteamGameServerServiceImpl.class);

    @Resource
    RestTemplate restTemplate;

    @Override
    public SteamGameServerStatusResponse getValheimInstanceStatus(String address, String key) throws Exception {
        final String url = STEAM_GAME_SERVER_SERVICE_URL + "?filter=\\appid\\892970\\addr\\" + address + ":2457&key=" + key;
        LOG.info("Sending GET using URL [" + url + "]");
        return restTemplate.getForObject(url, SteamGameServerStatusResponse.class);
    }
    
}
