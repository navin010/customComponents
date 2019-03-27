package com.adaptris.samples;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.adaptris.core.util.ExceptionHelper;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.validator.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@XStreamAlias("date-compare-service")
public class DateCompareService extends ServiceImp {

    @NotBlank
    private String startDateMetadataKey;
    @NotBlank
    private String endDateMetadataKey;
    @NotBlank
    private String outputMetadataKey;


    public void doService(AdaptrisMessage msg) throws ServiceException {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

            String startDateMetadataValue = msg.getMetadataValue(startDateMetadataKey);
            String endDateMetadataValue = msg.getMetadataValue(endDateMetadataKey);

            Date date1 = myFormat.parse(startDateMetadataValue);
            Date date2 = myFormat.parse(endDateMetadataValue);

            long difference = date1.getTime() - date2.getTime();
            long difference_days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            String difference_days_string = String.valueOf(difference_days);

            //System.out.println(difference_days_string);
            msg.addMetadata(outputMetadataKey, difference_days_string);

        } catch (Exception e) {
            ExceptionHelper.rethrowServiceException(e);
        }
    }

    public final void prepare() {
    }

    public final void initService() {
    }

    public final void closeService() {
    }

    public String getStartDateMetadataKey() {
        return startDateMetadataKey;
    }

    public void setStartDateMetadataKey(String startDateMetadataKey) {
        this.startDateMetadataKey = startDateMetadataKey;
    }

    public String getEndDateMetadataKey() {
        return endDateMetadataKey;
    }

    public void setEndDateMetadataKey(String endDateMetadataKey) {
        this.endDateMetadataKey = endDateMetadataKey;
    }

    public String getOutputMetadataKey() {
        return outputMetadataKey;
    }

    public void setOutputMetadataKey(String outputMetadataKey) {
        this.outputMetadataKey = outputMetadataKey;
    }
}
