package com.unikoeln.mazey.dhdexamplesecond.activities.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    private StringBuffer sb;

    private MessageDigest digest;

    public Security(String algorithm) {
        try {
            this.digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getHash(String stringToHash) {

        sb = new StringBuffer();
        byte[] result = digest.digest(stringToHash.getBytes());

        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
