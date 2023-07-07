package com.rixon.ramodage.provider;

import com.rixon.ramodage.configuration.Field;
import com.rixon.ramodage.util.DateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 22/1/13
 * Time: 12:04 PM
 * This class is used for testing the Date value provider
 */
public class TestDateValueProvider {

    private DateValueProvider dateValueProvider;

    @BeforeEach
    public void setUp() throws Exception {
        dateValueProvider = new DateValueProvider();
    }

    @AfterEach
    public void tearDown() {
        dateValueProvider = null;
    }

//    @Test
//    public void testDateValueProvider() {
//        for (int i=0;i<1000;i++) {
//            System.out.println("dateValueProvider = " + DateUtil.getDateAsString(dateValueProvider.randomValue()));
//        }
//    }

    @Test
    public void testDateValueProviderWithRange() {
        List<Date> values = Arrays.asList(DateUtil.getFormattedDate("01/01/2001"),
                DateUtil.getFormattedDate("01/03/2001"),DateUtil.getFormattedDate("05/05/2007"));
        for (int i=0;i<100;i++) {
            Date value = dateValueProvider.randomValueFromRange(values);
            assertTrue(values.contains(value));
            //System.out.println("value = " + DateUtil.getDateAsString(value));
        }

    }
//    @Test
//    public void testDateValueProviderAsString() {
//        Field<Date> field = mockField();
//        for (int i=0;i<1000;i++) {
//            System.out.println("date = " + dateValueProvider.randomValueAsString(field));
//        }
//    }

    @Test
    public void testDateFormatCheck(){
        for (int i=0;i<1000;i++){
            Date date = dateValueProvider.randomValue();
            assertNotNull(date);
            //System.out.printf("%s formatted(YYYYMMDD): %s \n",date.toString(),DateUtil.getFormattedDate(date,"dd-MM-YYYY"));
        }
    }

    @Test
    public void testDateValueBasedOnConstraints() {
        Field<Date> dateField = mockField();
        Date minDate = DateUtil.getFormattedDate(dateField.getMinValue(),dateField.getFormatMask());
        Date maxDate = DateUtil.getFormattedDate(dateField.getMaxValue(),dateField.getFormatMask());
        for (int i=0;i<10000;i++) {
            Date date = dateValueProvider.randomValueFromRange(minDate,maxDate);
            assertThat("Date is less than minDate :"+date,date,greaterThanOrEqualTo(minDate));
            assertThat("Date is more than maxDate :"+date,date,lessThanOrEqualTo(maxDate));
        }
    }

    private Field<Date> mockField() {
        Field<Date> dateField = new Field<>();
        dateField.setType("date");
        dateField.setFormatMask("yyyyMMdd");
        dateField.setFixedLength(8);
        dateField.setMinValue("20000101");
        dateField.setMaxValue("20141231");
        return dateField;
    }
}