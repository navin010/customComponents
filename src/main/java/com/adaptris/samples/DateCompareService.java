package com.adaptris.samples;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.adaptris.interlok.InterlokException;
import com.adaptris.interlok.config.DataInputParameter;
import com.adaptris.interlok.config.DataOutputParameter;
import com.adaptris.core.util.ExceptionHelper;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.validator.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@XStreamAlias("date-compare-service")
public class DateCompareService extends ServiceImp {

    @NotBlank
    //private String startDate;
    private DataInputParameter<String> startDate;
    @NotBlank
    //private String endDate;
    private DataInputParameter<String> endDate;


    public void doService(AdaptrisMessage msg) throws ServiceException {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            //Date date1 = myFormat.parse(startDate);
            //Date date2 = myFormat.parse(endDate);
            String date1 = (String)((DataInputParameter)getStartDate()).extract(msg);           //cast to string
            String date2 = (String)((DataInputParameter)getEndDate()).extract(msg);                //cast to string

            long difference = date1.getTime() - date2.getTime();
            long difference_days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            String difference_days_string = String.valueOf(difference_days);
            System.out.println(difference_days_string);
        } catch (Exception | InterlokException e) {
            ExceptionHelper.rethrowServiceException(e);
        }
    }

    public final void prepare() {
    }

    public final void initService() {
    }

    public final void closeService() {
    }

    public DataInputParameter<String> getStartDate() {
        return startDate;
    }

    public void setStartDate(DataInputParameter<String> startDate) {
        this.startDate = startDate;
    }

    public DataInputParameter<String> getEndDate() {
        return endDate;
    }

    public void setEndDate(DataInputParameter<String> endDate) {
        this.endDate = endDate;
    }
}