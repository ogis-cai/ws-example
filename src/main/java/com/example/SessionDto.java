package com.example;

/**
 * Created by one on 17/04/16.
 */
public class SessionDto {

    private final String id;
    private final long timeStamp;


    public SessionDto(String id, long timeStamp) {
        this.id = id;
        this.timeStamp = timeStamp;
    }


    public String getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
