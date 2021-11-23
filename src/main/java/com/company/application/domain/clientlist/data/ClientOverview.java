package com.company.application.domain.clientlist.data;

import com.company.application.domain.core.data.IClient;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ClientOverview implements IClient {
    private final int id;
    private String name;
    private String representative;
    private String email;
    private String phone;

    public ClientOverview(int id, String name, String representative, String email, String phone) {
        this.id = id;
        this.name = name;
        this.representative = representative;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public Integer getId() { return id; }
    @Override
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String getRepresentative() { return representative; }
    public void setRepresentative(String representative) { this.representative = representative; }
    @Override
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
