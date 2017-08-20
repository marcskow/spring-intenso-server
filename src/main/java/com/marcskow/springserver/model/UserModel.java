package com.marcskow.springserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "user")
public class UserModel {
    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    private String password;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String email;

    @JsonIgnore
    private List<String> privilages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPrivilages() {
        return privilages;
    }

    public void setPrivilages(List<String> privilages) {
        this.privilages = privilages;
    }

    public void grantPrivilage(String privilage) {
        if(privilages == null) {
            privilages = new ArrayList<>();
        }
        privilages.add(privilage);
    }

    public void removePrivilage(String privilage) {
        if(privilages == null) {
            return;
        }
        privilages.remove(privilage);
    }
}
