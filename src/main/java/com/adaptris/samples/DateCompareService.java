package com.adaptris.samples;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.adaptris.core.util.ExceptionHelper;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.validator.constraints.NotBlank;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.AffectsMetadata;
import com.adaptris.annotation.AutoPopulated;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;


@XStreamAlias("date-compare-service")
@AdapterComponent
@ComponentProfile(summary = "Find the difference in days between two date values", tag = "service,timestamp,datetime")
@DisplayOrder(order = {"dateFormat", "unitOfTime", "startDateMetadataKey", "endDateMetadataKey", "outputMetadataKey"})
public class DateCompareService extends ServiceImp {

    @NotBlank
    private String dateFormat = "yyyy-MM-dd";

    @AutoPopulated
    @NotNull
    private com.adaptris.samples.TimeUnit unitOfTime = com.adaptris.samples.TimeUnit.DAYS;

    @NotBlank
    private String startDateMetadataKey;

    @NotBlank
    private String endDateMetadataKey;

    @NotBlank
    @AffectsMetadata
    private String outputMetadataKey;

    private transient String startDateMetadataValue;
    private transient String endDateMetadataValue;
    //private transient String daysDifferenceValue;
    private transient String unitDifferenceValue;


    public void doService(AdaptrisMessage msg) throws ServiceException {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);     //e.g. "yyyy-MM-dd"

            startDateMetadataValue = msg.getMetadataValue(startDateMetadataKey);
            endDateMetadataValue = msg.getMetadataValue(endDateMetadataKey);

            Date date1 = myFormat.parse(startDateMetadataValue);
            Date date2 = myFormat.parse(endDateMetadataValue);

            long difference = date1.getTime() - date2.getTime();

            //long difference_days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            //daysDifferenceValue = String.valueOf(difference_days);      //convert to string

            long difference_unit = TimeUnit.valueOf(unitOfTime.toString()).convert(difference, TimeUnit.MILLISECONDS);
            unitDifferenceValue = String.valueOf(difference_unit);

            msg.addMetadata(outputMetadataKey, unitDifferenceValue);

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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getStartDateMetadataValue() {
        return startDateMetadataValue;
    }

    public void setStartDateMetadataValue(String startDateMetadataValue) {
        this.startDateMetadataValue = startDateMetadataValue;
    }

    public String getEndDateMetadataValue() {
        return endDateMetadataValue;
    }

    public void setEndDateMetadataValue(String endDateMetadataValue) {
        this.endDateMetadataValue = endDateMetadataValue;
    }

    /*
    public String getDaysDifferenceValue() {
        return daysDifferenceValue;
    }

    public void setDaysDifferenceValue(String daysDifferenceValue) {
        this.daysDifferenceValue = daysDifferenceValue;
    }
    */

    public com.adaptris.samples.TimeUnit getUnitOfTime() {
        return unitOfTime;
    }

    public void setUnitOfTime(com.adaptris.samples.TimeUnit unitOfTime) {
        this.unitOfTime = unitOfTime;
    }

    public String getUnitDifferenceValue() {
        return unitDifferenceValue;
    }

    public void setUnitDifferenceValue(String unitDifferenceValue) {
        this.unitDifferenceValue = unitDifferenceValue;
    }
}
