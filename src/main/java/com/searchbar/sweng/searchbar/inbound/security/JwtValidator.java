package com.searchbar.sweng.searchbar.inbound.security;

import com.searchbar.sweng.searchbar.model.Exceptions.ResourceNotFoundException;
import com.searchbar.sweng.searchbar.model.Role;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtValidator {

    @Autowired
    private PublicKeyProvider keys;

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public boolean isValidJWT(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new ResourceNotFoundException("JWT invalid");
        }
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(getUserEmail(token), "", getRoles(token));
    }

    public String getUserEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Collection<GrantedAuthority> getRoles(String token) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        String temp = Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token).getBody().get("auth", String.class);
        if(temp.equals("USER")){
            result.add(Role.NORMAL);
        }else if(temp.equals("ADMIN")){
            result.add(Role.ADMIN);
        }else if(temp.equals("PREMIUM")){
            result.add(Role.PREMIUM);
        }
        return result;
    }

}
