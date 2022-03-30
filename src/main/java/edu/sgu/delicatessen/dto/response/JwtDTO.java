package edu.sgu.delicatessen.dto.response;

public class JwtDTO  {
    private String jwt;
    private String username;
    private  String type;

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
