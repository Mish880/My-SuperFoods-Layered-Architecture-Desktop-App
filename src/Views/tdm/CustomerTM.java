package Views.tdm;

public class CustomerTM implements Comparable<CustomerTM>{

        private String id;
        private String title;
        private String Name;
        private String Address;
        private String city;
        private String Province;
        private String PostalCode;

    public CustomerTM() {
    }

    public CustomerTM(String id, String title, String name, String address, String city, String province, String postalCode) {
        this.setId(id);
        this.setTitle(title);
        setName(name);
        setAddress(address);
        this.setCity(city);
        setProvince(province);
        setPostalCode(postalCode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", city='" + city + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }


    @Override
    public int compareTo(CustomerTM o) {
        return id.compareTo(o.getId());
    }
}

