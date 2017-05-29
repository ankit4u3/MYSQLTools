/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqltools;

import java.sql.Connection;
import java.util.StringTokenizer;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author Developer
 *
 *
 */
public class DelayObject implements Delayed {

    private String data;
    private Connection connect = null;
    String tag;//= s.nextToken().replace("TAG_ID: ", "");
    String timestamp;//= s.nextToken().replace("TIMESTAMP: ", "");
    String hash;//s.nextToken().replace("HASH: ", "");
    String antenna;// s.nextToken().replace("ANTENNA_ID: ", "");

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAntenna() {
        return antenna;
    }

    public void setAntenna(String antenna) {
        this.antenna = antenna;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    private long startTime;

    public DelayObject(String data, long delay) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
        parseTag(data);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((DelayObject) o).startTime) {
            return -1;
        }
        if (this.startTime > ((DelayObject) o).startTime) {
            return 1;
        }
        return 0;
    }

    public void parseTag(final String payload) {
        if (payload.startsWith("TAG_ID:")) {
            try {

                String headerLine = payload;
                StringTokenizer s = new StringTokenizer(headerLine, ",");
                tag = s.nextToken().replace("TAG_ID: ", "");
                timestamp = s.nextToken().replace("TIMESTAMP: ", "");
                hash = s.nextToken().replace("HASH: ", "");
                antenna = s.nextToken().replace("ANTENNA_ID: ", "");
            } catch (Exception e) {

            }
        }
    }

    @Override
    public String toString() {
        return data;
    }
}
