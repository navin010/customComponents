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
    adaptrisMessage.addMetadata("key2", "2019-01-04");
    DateCompareService dcs = new DateCompareService();
    dcs.setStartDateMetadataKey("key1");
    dcs.setEndDateMetadataKey("key2");
    dcs.setDateFormat("yyyy-MM-dd");
    dcs.setOutputMetadataKey("daysDifference");
    dcs.doService(adaptrisMessage);
    String difference = dcs.getDaysDifferenceValue();
    assertEquals("4", difference);            //expected (String) vs actual (String)
  }

  @Override
  protected Object retrieveObjectForSampleConfig() {
    DateCompareService service = new DateCompareService();
    service.setDateFormat("yyyy-MM-dd");
    service.setStartDateMetadataKey("key1");
    service.setStartDateMetadataValue("2019-01-08");
    service.setEndDateMetadataKey("key2");
    service.setEndDateMetadataValue("2019-01-04");
    service.setOutputMetadataKey("daysDifference");
    return service;
  }

}
