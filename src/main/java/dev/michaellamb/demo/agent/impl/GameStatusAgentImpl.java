package dev.michaellamb.demo.agent.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.michaellamb.demo.agent.GameStatusAgent;
import dev.michaellamb.demo.model.GameStatusResponse;
import dev.michaellamb.demo.model.external.SteamGameServerStatusResponse;
import dev.michaellamb.demo.service.SteamGameServerService;

@Service
public class GameStatusAgentImpl implements GameStatusAgent {
    
    private static final Logger LOG = LoggerFactory.getLogger(GameStatusAgentImpl.class);

    @Resource
    SteamGameServerService steamGameServerService;

    @Override
    public GameStatusResponse getValheimInstanceStatus(String address, String key) {
        final GameStatusResponse response = new GameStatusResponse();
        try {
            SteamGameServerStatusResponse gameStatus = steamGameServerService.getValheimInstanceStatus(address, key);
            if (gameStatus.getResponse().getServers() != null) {
                LOG.info(gameStatus.toString());
                response.setUp(gameStatus.getResponse().getServers().size() > 0);
            }
        } catch (Exception e) {
            LOG.error("An error occurred fetching Steam Game Server Status", e);
        }
        return response;
    }
}
