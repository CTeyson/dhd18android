package com.unikoeln.mazey.dhdexamplesecond.activities.data.http;

import com.unikoeln.mazey.dhdexamplesecond.activities.utils.Security;

import java.sql.Timestamp;

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
        timestamp = new Timestamp(System.currentTimeMillis());
        endpointEventor = "https://www.conftool.com/dhd2018/eventor.php?";
        endpointC4me = "https://www.conftool.com/dhd2018/c4me.php?";
        timestampAsString = String.valueOf(timestamp.getTime());
        keyEventor = "shZUfkNE";
        keyC4me = "4umDj98Z";
        stringToHashC4me = String.format("%1s%2s", timestampAsString, keyC4me);
        stringToHashEventor = String.format("%1s%2s", timestampAsString, keyEventor);
    }

    public String getResourceUrlEventor() {
        String sha256 = security.getHash(stringToHashEventor);
        return String.format("%1s%2s%3s%4s%5s", endpointEventor, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

    public String getResourceUrlC4me() {
        String sha256 = security.getHash(stringToHashC4me);
        return String.format("%1s%2s%3s%4s%5s", endpointC4me, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

}
