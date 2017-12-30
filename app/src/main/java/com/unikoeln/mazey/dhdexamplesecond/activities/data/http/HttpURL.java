package com.unikoeln.mazey.dhdexamplesecond.activities.data.http;

import com.unikoeln.mazey.dhdexamplesecond.activities.utils.Security;

import java.sql.Timestamp;

// TODO: Remove senstive data like passwords and keys

public class HttpURL {

    private Timestamp timestamp = null;
    private String endpointEventor = null;
    private String endpointC4me = null;
    private String timestampAsString = null;
    private String keyEventor = null;
    private String keyC4me = null;
    private String stringToHashC4me = null;
    private String stringToHashEventor = null;

    private Security security = new Security("SHA-256");

    public HttpURL() {
        endpointEventor = "https://www.conftool.com/dhd2018/eventor.php?";
        endpointC4me = "https://www.conftool.com/dhd2018/c4me.php?";
        keyEventor = "";
        keyC4me = "";
    }

    // TODO Check why timestamp is behaving strangely

    public String getResourceUrlEventor() {
        timestamp = new Timestamp(System.currentTimeMillis() + 1000000);
        timestampAsString = String.valueOf(timestamp.getTime());
        stringToHashEventor = String.format("%1s%2s", timestampAsString, keyEventor);
        String sha256 = security.getHash(stringToHashEventor);
        return String.format("%1s%2s%3s%4s%5s", endpointEventor, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

    public String getResourceUrlC4me() {
        timestamp = new Timestamp(System.currentTimeMillis() + 10000000);
        timestampAsString = String.valueOf(timestamp.getTime());
        stringToHashC4me = String.format("%1s%2s", timestampAsString, keyC4me);
        String sha256 = security.getHash(stringToHashC4me);
        return String.format("%1s%2s%3s%4s%5s", endpointC4me, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

}
