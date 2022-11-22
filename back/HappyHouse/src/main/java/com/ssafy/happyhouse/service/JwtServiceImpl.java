package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
    private static final String SALT = "ssafySecret";
    private static final int EXPIRE_MINUTES = 60;

    @Override
    public <T> String create(String key, T data, String subject) {
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES)).setSubject(subject)
                .claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (logger.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                logger.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }
        return key;
    }

    @Override
    public boolean isUsable(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String jwt = request.getHeader("access-token");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }
        Map<String, Object> value = claims.getBody();
        logger.info("value : {}", value);
        return value;
    }

    @Override
    public String getUserId() {
        return (String) this.get("user").get("userid");
    }
}
