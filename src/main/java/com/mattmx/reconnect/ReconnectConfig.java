package com.mattmx.reconnect;

import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.List;

public class ReconnectConfig {

    @Comment("Do we want to send players a message when they are reconnected to a server?")
    public boolean messageOnReconnect = true;
    public List<String> reconnectMessage = List.of("<gray>You were reconnected to <white>%server%</white>.");

    @Comment("Do we want to send a message when their previous server isn't available?")
    public boolean notAvailable = true;
    public List<String> notAvailableMessage = List.of("<gray>Unable to reconnect you to your last server.");

    @Comment("""
        For each server, do want to make sure that user has permissions to reconnect to that server
                
        permission: velocity.reconnect.%servername%
        """)
    public boolean perServerPermission = false;

    @Comment("List any servers that you do not want people to reconnect to.")
    public List<String> blacklist = List.of();

    @Comment("""
        If set to true, prevents connection to fallback servers.
        Server should not be on the blacklist.
        """)
    public boolean preventFallback = false;
    public List<String> preventFallbackMessage = List.of();

    @Comment("LiteBans hook - If set to true, hooks into the LiteBans api to prevent players from reconnecting to banned servers")
    public boolean liteBansHook = false;

    @Comment("""
        ======================= Storage Configuration =======================
        Configure your storage options.
        
        Default type: yaml
        Options:
            - mysql/mariadb
            - yaml
            - sqlite
            - luckperms
        """)
    public StorageOptions storage = new StorageOptions();

    static class StorageOptions {
        public String method = "yaml";

        public StorageOptionsData data = new StorageOptionsData();
    }

    static class StorageOptionsData {
        public String address = "localhost";
        @Comment("For sqlite or yaml storage types this will be the file location.")
        public String database = "reconnect.db";
        public String username = "root";
        public String password = "1234";

        @Comment("""
            Advanced connection pool settings. Most users will not need to change these.
            https://github.com/brettwooldridge/HikariCP/blob/dev/README.md#gear-configuration-knobs-baby
            """)
        public AdvancedConnectionOptions connectionOptions = new AdvancedConnectionOptions();
    }

    static class AdvancedConnectionOptions {
        public boolean useJdbcString = false;
        public String jdbcString = "jdbc:mysql://host:3306/db";
        public long connectionTimeout = 30000;
        public long idleTimeout = 600000;
        public long keepAliveTime = 0;
        public long maxLifetime = 1800000;
        public long minimumIdle = 10;
        public long maximumPoolSize = 10;
    }
}
