package dev.michaellamb.demo.model.external;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamGameServerStatusResponse {
    public static class Response {
        private List<Server> servers;

        public List<Server> getServers() {
            return servers;
        }

        public void setServers(List<Server> servers) {
            this.servers = servers;
        }
    }

    public static class Server {
        private String addr;
        private Integer gameport;
        private String steamid;
        private String name;
        private Integer appid;
        private String gamedir;
        private String version;
        private String product;
        private Integer region;
        private Integer players;
        @JsonProperty("max_players")
        private Integer maxPlayers;
        private Integer bots;
        private String map;
        private Boolean secure;
        private Boolean dedicated;
        private String os;
        private String gametype;

        public String getAddr() {
            return addr;
        }
        public Integer getMaxPlayers() {
            return maxPlayers;
        }
        public void setMaxPlayers(Integer maxPlayers) {
            this.maxPlayers = maxPlayers;
        }
        public String getGametype() {
            return gametype;
        }
        public void setGametype(String gametype) {
            this.gametype = gametype;
        }
        public String getOs() {
            return os;
        }
        public void setOs(String os) {
            this.os = os;
        }
        public Boolean getDedicated() {
            return dedicated;
        }
        public void setDedicated(Boolean dedicated) {
            this.dedicated = dedicated;
        }
        public Boolean getSecure() {
            return secure;
        }
        public void setSecure(Boolean secure) {
            this.secure = secure;
        }
        public String getMap() {
            return map;
        }
        public void setMap(String map) {
            this.map = map;
        }
        public Integer getBots() {
            return bots;
        }
        public void setBots(Integer bots) {
            this.bots = bots;
        }
        public Integer getPlayers() {
            return players;
        }
        public void setPlayers(Integer players) {
            this.players = players;
        }
        public Integer getRegion() {
            return region;
        }
        public void setRegion(Integer region) {
            this.region = region;
        }
        public String getProduct() {
            return product;
        }
        public void setProduct(String product) {
            this.product = product;
        }
        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
        public String getGamedir() {
            return gamedir;
        }
        public void setGamedir(String gamedir) {
            this.gamedir = gamedir;
        }
        public Integer getAppid() {
            return appid;
        }
        public void setAppid(Integer appid) {
            this.appid = appid;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSteamid() {
            return steamid;
        }
        public void setSteamid(String steamid) {
            this.steamid = steamid;
        }
        public Integer getGameport() {
            return gameport;
        }
        public void setGameport(Integer gameport) {
            this.gameport = gameport;
        }
        public void setAddr(String addr) {
            this.addr = addr;
        }

        @Override
        public String toString() {
            return "addr:" + this.addr
            + " name:" + this.name
            + " version:" + this.version;
        }
    }

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        if (this.response.getServers().isEmpty()) {
            return "response empty";
        }
        return "response: " + this.getResponse().getServers().toString();
    }
}
