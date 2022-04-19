package dev.michaellamb.demo.agent;

import dev.michaellamb.demo.model.GameStatusResponse;

public interface GameStatusAgent {
    GameStatusResponse getValheimInstanceStatus(String address, String key);
}
