package book.chapter06;

public class TestApplication {
    public static void main(String[] args){
        Data data = new Data("공유", "KR");
        Organization organization = new Organization(data);
    }
}

class Data {
    private String title;
    private String country;

    public Data(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

class Organization {
    private String name;
    private String country;

    public Organization(Data data) {
        this.name = data.getTitle();
        this.country = data.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
