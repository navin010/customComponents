package com.adaptris.samples;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.AdaptrisMessageFactory;
import com.adaptris.core.ServiceCase;
import com.adaptris.core.ServiceException;
import static org.junit.Assert.assertEquals;

public class DateCompareServiceTest extends ServiceCase {

  public DateCompareServiceTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
  }

  public void testService() throws Exception {
    AdaptrisMessage adaptrisMessage = AdaptrisMessageFactory.getDefaultInstance().newMessage();
    adaptrisMessage.addMetadata("key1", "2019-01-08");
    adaptrisMessage.addMetadata("key2", "2019-01-07");
    DateCompareService dcs = new DateCompareService();
    dcs.setStartDateMetadataKey("key1");
    dcs.setEndDateMetadataKey("key2");
    dcs.setDateFormat("yyyy-MM-dd");
    dcs.setOutputMetadataKey("daysDifference");

    dcs.setUnitOfTime(TimeUnit.DAYS);
    dcs.doService(adaptrisMessage);
    String difference_days = dcs.getUnitDifferenceValue();
    assertEquals("1", difference_days);            //expected (String) vs actual (String)

    dcs.setUnitOfTime(TimeUnit.HOURS);
    dcs.doService(adaptrisMessage);
    String difference_hours = dcs.getUnitDifferenceValue();
    assertEquals("24", difference_hours);            //expected (String) vs actual (String)

    dcs.setUnitOfTime(TimeUnit.MINUTES);
    dcs.doService(adaptrisMessage);
    String difference_minutes = dcs.getUnitDifferenceValue();
    assertEquals("1440", difference_minutes);            //expected (String) vs actual (String)

    dcs.setUnitOfTime(TimeUnit.SECONDS);
    dcs.doService(adaptrisMessage);
    String difference_seconds = dcs.getUnitDifferenceValue();
    assertEquals("86400", difference_seconds);            //expected (String) vs actual (String)

  }

  @Override
  protected Object retrieveObjectForSampleConfig() {
    DateCompareService service = new DateCompareService();
    service.setDateFormat("yyyy-MM-dd");
    service.setStartDateMetadataKey("key1");
    service.setEndDateMetadataKey("key2");
    service.setOutputMetadataKey("key3");
    service.setUnitOfTime(TimeUnit.DAYS);
    return service;
  }

}
