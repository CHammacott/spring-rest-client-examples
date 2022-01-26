package guru.springframework.api.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserData {

    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public void ingestApiUsers (List<LinkedHashMap<String, Object>> apiUsers){
        List<User> users = new ArrayList<>();
        for (LinkedHashMap<String, Object> apiUser : apiUsers){
            User user = new User();
            user.setId(apiUser.get("id").toString());
            user.setName((String) apiUser.get("name"));
            user.setEmail((String) apiUser.get("email"));
            user.setAddress(ingestAddress((LinkedHashMap<String, Object>) apiUser.get("address")));
            user.setPhone((String) apiUser.get("phone"));
            user.setWebsite((String) apiUser.get("website"));
            user.setCompany(ingestCompany((LinkedHashMap<String, String>) apiUser.get("company")));

            users.add(user);
        }
        setData(users);
    }

    private Address ingestAddress(LinkedHashMap<String, Object> apiAddressData){
        Address address = new Address();
        address.setStreet((String) apiAddressData.get("street"));
        address.setSuite((String) apiAddressData.get("suite"));
        address.setCity((String) apiAddressData.get("city"));
        address.setZipcode((String) apiAddressData.get("zipcode"));
        address.setGeo(ingestGeo((LinkedHashMap<String, String>) apiAddressData.get("geo")));
        return address;
    }

    private Geo ingestGeo (LinkedHashMap<String, String> apiGeoData){
        Geo geo = new Geo();
        geo.setLat(apiGeoData.get("lat"));
        geo.setLng(apiGeoData.get("lng"));
        return geo;
    }

    private Company ingestCompany (LinkedHashMap<String, String> apiCompanyData){
        Company company =  new Company();
        company.setName(apiCompanyData.get("name"));
        company.setCatchPhrase(apiCompanyData.get("catchPhrase"));
        company.setBs(apiCompanyData.get("bs"));
        return company;
    }
}
