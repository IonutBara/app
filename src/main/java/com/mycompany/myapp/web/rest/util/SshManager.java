package com.mycompany.myapp.web.rest.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ibara on 3/20/2017.
 */
public class SshManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SshManager.class);
    private static final int TIMEOUT = 60000;
    private static final int PORT = 22;
    private JSch jsch;
    private Session session;
    private Channel channel;
    private String user;
    private String host;
    private String password;

    public SshManager(String user, String host, String password) {
        this.user = user;
        this.host = host;
        this.password = password;
        jsch = new JSch();
    }

    /*public static void main(String[] args) {
        long a = System.currentTimeMillis ();
        System.out.print("curentTime:\n"+a);
        SshManager ssh = new SshManager("root", "10.241.6.117", "augusta");
        //SshManager ssh = new SshManager("root", "10.241.6.107", "ugly");
        ssh.connect();
        //ssh.runCommand("ls \n");
        //ssh.runCommand("echo -e test test again works | tee -a /root/ibara.txt \n");
    }*/

    public JSch getJsch() {
        return jsch;
    }

    public void setJsch(JSch jsch) {
        this.jsch = jsch;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void connect() {
        try {
            session = jsch.getSession(user, host, PORT);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(TIMEOUT);
            while (!session.isConnected()) {
                LOGGER.info("Waiting for connection to be up and running..");
                session.connect(TIMEOUT);
            }
            channel = session.openChannel("exec");
            channel.connect();
        } catch (JSchException e) {
            LOGGER.error("JSchException e: {}", e);
        }
        LOGGER.debug("Connected successfully!");
    }

    private void runCommand(String command) {
        Channel channel = null;
        InputStream commandOutput = null;
        try {
            channel = session.openChannel("exec");
            assert (channel) != null;
            ((ChannelExec) channel).setCommand(command);
            commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();
            while (readByte != 0xffffffff) {
                readByte = commandOutput.read();
            }
            LOGGER.debug("Executed command: {}", command);
        } catch (JSchException e) {
            LOGGER.error("JSchException e: {}", e);
        } catch (IOException e) {
            LOGGER.error("IOException e: {}", e);
        } finally {
            try {
                assert commandOutput != null;
                commandOutput.close();
            } catch (IOException e) {
                LOGGER.error("IOException e: {}", e);
            }
            channel.disconnect();
        }
    }
}
