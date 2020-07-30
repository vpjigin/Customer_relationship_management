package in.solocrew.cms.appdb;

public class Shops {
    int id;
    String name,address,location,contact_number;

    public Shops() {
    }

    public Shops(String toString, String toString1, String location, String toString2) {
        this.name = toString;
        this.address = toString1;
        this.location = location;
        this.contact_number = toString2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
