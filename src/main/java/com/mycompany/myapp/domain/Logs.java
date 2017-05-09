package com.mycompany.myapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by ibara on 2/28/2017.
 */
@Entity
@Table(name = "logs")
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "logger")
    private String logger;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private ZonedDateTime date = null;

    public Logs(String user_id, String logger, String level, String message) { //, ZonedDateTime date) {
        this.user_id = user_id;
        this.logger = logger;
        this.level = level;
        this.message = message;
        //this.date = date;
    }

    public Logs() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logs)) return false;

        Logs logs = (Logs) o;

        if (user_id != null ? !user_id.equals(logs.user_id) : logs.user_id != null) return false;
        if (!level.equals(logs.level)) return false;
        if (!message.equals(logs.message)) return false;
        return date.equals(logs.date);

    }

    @Override
    public int hashCode() {
        int result = user_id != null ? user_id.hashCode() : 0;
        result = 31 * result + level.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Logs{" +
            "id=" + id +
            ", user_id='" + user_id + '\'' +
            ", logger='" + logger + '\'' +
            ", level='" + level + '\'' +
            ", message='" + message + '\'' +
            ", date=" + date +
            '}';
    }
}

