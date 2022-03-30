package edu.sgu.delicatessen.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.sgu.delicatessen.entity.User;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

  
    private String username;
  
    @JsonIgnore
    private String password;
  
    private Collection<? extends GrantedAuthority> authorities;
  
    public MyUserDetails( String username ,String password,
        Collection<? extends GrantedAuthority> authorities) {
      this.username = username;
      this.password = password;
      this.authorities = authorities;
    }
  
    public static MyUserDetails build(User user) {
      List<GrantedAuthority> authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
  
      return new MyUserDetails(
          user.getUsername(), 
          user.getPassword(), 
          authorities);
    }
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
    }
  
    @Override
    public String getPassword() {
      return password;
    }
  
    @Override
    public String getUsername() {
      return username;
    }
  
    @Override
    public boolean isAccountNonExpired() {
      return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
      return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }
  
    @Override
    public boolean isEnabled() {
      return true;
    }
  
   
    
}
