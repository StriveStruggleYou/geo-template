package io.github.ssy.geo.template;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoTemplateApplication {

  public static void main(String args[]) throws IOException {

    //数据库文件信息
    File database = new File(
      GeoTemplateApplication.class.getClass().getResource("/GeoLite2-City.mmdb").getPath());

    DatabaseReader reader = new DatabaseReader.Builder(database).build();
    try {
      InetAddress ipAddress = InetAddress.getByName("122.224.223.239");

      //  Use the enterprise(ip) method to do a lookup in the Enterprise database

      CityResponse response = reader.city(ipAddress);

      Country country = response.getCountry();
      System.out.println(country.getIsoCode());            // 'US'
      System.out.println(country.getName());               // 'United States'
      System.out.println(country.getNames().get("zh-CN")); // '美国'

      Subdivision subdivision = response.getMostSpecificSubdivision();
      System.out.println(subdivision.getName());    // 'Minnesota'
      System.out.println(subdivision.getIsoCode()); // 'MN'

      City city = response.getCity();
      System.out.println(city.getName()); // 'Minneapolis'

      Postal postal = response.getPostal();
      System.out.println(postal.getCode()); // '55455'

      Location location = response.getLocation();
      System.out.println(location.getLatitude());  // 44.9733
      System.out.println(location.getLongitude()); // -93.2323

    } catch (Exception e) {

    }

  }

}
