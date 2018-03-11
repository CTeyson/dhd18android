package com.unikoeln.mazey.dhdexamplesecond.activities.data.http;

import com.unikoeln.mazey.dhdexamplesecond.activities.utils.security.Security;

// TODO: Remove senstive data like passwords and keys

public class HttpURL {

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
        timestampAsString = String.valueOf(System.currentTimeMillis());
        stringToHashEventor = String.format("%1s%2s", timestampAsString, keyEventor);
        String sha256 = security.getHash(stringToHashEventor);
        return String.format("%1s%2s%3s%4s%5s", endpointEventor, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

    public String getResourceUrlC4me() {
        timestampAsString = String.valueOf(System.currentTimeMillis());
        stringToHashC4me = String.format("%1s%2s", timestampAsString, keyC4me);
        String sha256 = security.getHash(stringToHashC4me);
        return String.format("%1s%2s%3s%4s%5s", endpointC4me, "&nonce=", timestampAsString, "&passhash=", sha256);
    }

}
