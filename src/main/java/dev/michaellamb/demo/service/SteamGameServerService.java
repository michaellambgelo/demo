package dev.michaellamb.demo.service;

import dev.michaellamb.demo.model.external.SteamGameServerStatusResponse;

public interface SteamGameServerService {
    SteamGameServerStatusResponse getValheimInstanceStatus(String address, String key) throws Exception;
}
