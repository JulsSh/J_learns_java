package ru.stqa.pft.rest.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.90.246.218");
    assertEquals(geoIp, "DE");
    System.out.println(geoIp);
  }

  @Test
  public void testInvalidIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.90.246.xxx");
    assertEquals(geoIp, "DE");
    System.out.println(geoIp);
  }
}
