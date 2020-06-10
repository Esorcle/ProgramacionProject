package es.esorcle.swing.db;

public class ConnectionData {

    private String server;
    private String port;
    private String db;
    private String user;
    private char[] pwd;

    public ConnectionData(String server, String port, String db, String user, char[] pwd) {

        this.server = server;
        this.port = port;
        this.db = db;
        this.user = user;
        this.pwd = pwd;
    }

    public String getConectionUrlMySql() {
        String url = "jdbc:mysql://" + this.server + ":"+ this.port + "/" + this.db + "?autoReconnect=true&serverTimezone=UTC";
        return url;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public char[] getPwd() {
        return pwd;
    }

    public void setPwd(char[] pwd) {
        this.pwd = pwd;
    }
}
