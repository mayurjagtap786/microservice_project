package com.example.auth.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CompromisedPasswordCheckerImpl implements CompromisedPasswordChecker {

    private static final String API_URL = "https://api.pwnedpasswords.com/range/";
    private final MessageDigest sha1Digest;
    private static final int PREFIX_LENGTH = 5;
    private RestClient restClient = RestClient.builder().baseUrl(API_URL).build();

    private final Log logger = LogFactory.getLog(getClass());

    public CompromisedPasswordCheckerImpl() {
        this.sha1Digest =  getSha1Digest();
    }

    @Override
    public CompromisedPasswordDecision check(String password) {
        byte[] hash = this.sha1Digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = new String(Hex.encode(hash)).toUpperCase(Locale.ROOT);
        String prefix = encoded.substring(0, PREFIX_LENGTH);
        String suffix = encoded.substring(PREFIX_LENGTH);

        List<String> passwords = getLeakedPasswordsForPrefix(prefix);
        boolean isLeaked = findLeakedPassword(passwords, suffix);
        return new CompromisedPasswordDecision(isLeaked);
    }

    private List<String> getLeakedPasswordsForPrefix(String prefix) {
        try {
            String response = this.restClient.get().uri(prefix).retrieve().body(String.class);
            if (!StringUtils.hasText(response)) {
                return Collections.emptyList();
            }
            return response.lines().toList();
        }
        catch (RestClientException ex) {
            this.logger.error("Request for leaked passwords failed", ex);
            return Collections.emptyList();
        }
    }

    private boolean findLeakedPassword(List<String> passwords, String suffix) {
        for (String pw : passwords) {
            if (pw.startsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    private static MessageDigest getSha1Digest() {
        try {
            return MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
