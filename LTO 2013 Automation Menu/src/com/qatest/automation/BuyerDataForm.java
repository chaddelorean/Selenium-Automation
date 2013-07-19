package com.qatest.automation;

/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 7/19/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class BuyerDataForm {
    private String login;
    private String password;
    private String distID;
    private String buyerID;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String postalcode;

    public BuyerDataForm()
    {
        email = "test@test.com";
        phone = "999-9999";
        address = "75 Test Street";
        city = "Provo";
        state = "UT";
        postalcode = "84601";
    }

    public BuyerDataForm(String l, String p, String d, String b, String e, String ph, String adr, String c, String s, String post)
    {
        this.login = l;
        this.password = p;
        this.distID = d;
        this.buyerID = b;
        this.email = e;
        this.phone = ph;
        this.address = adr;
        this.city = c;
        this.state = s;
        this.postalcode = post;
    }

    public void setLogin(String l)
    {
         login = l;
    }

    public void setPassword(String pass)
    {
        password = pass;
    }

    public void setDistID(String dist)
    {
        distID = dist;
    }

    public void setBuyerID(String buyer)
    {
        buyerID = buyer;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getDistID()
    {
        return distID;
    }

    public String getBuyerID()
    {
        return buyerID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalcode() {
        return postalcode;
    }
}
