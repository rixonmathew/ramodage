package com.rixon.ramodage.provider;

import com.rixon.ramodage.util.DateUtil;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 22/1/13
 * Time: 12:22 PM
 * This class will provide random values based on a set of values defined in a file.
 */
class DateProvider {

    private final static Logger LOG = Logger.getLogger(DateProvider.class);
    private static final List<Date> dateList;
    private static final String DATES_FILE = "dates.txt";
    private static final Random random;


    static{
        dateList = new ArrayList<Date>();
        random = new Random();
    }

    public static Date randomDate() {
        if (dateList.isEmpty()) {
            try{
              datesFormFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int index = random.nextInt(dateList.size());
        return dateList.get(index);
    }

    private static void datesFormFile() throws IOException {
        final ClassLoader resourceLoader = Thread.currentThread().getContextClassLoader();
        InputStream datesFile = resourceLoader.getResourceAsStream(DATES_FILE);
        if (datesFile == null) {
            LOG.error("Dates file not found :"+DATES_FILE);
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(datesFile));
        String line = br.readLine();
        while (line != null) {
            if (line != null)
                dateList.add(DateUtil.getFormattedDate(line));
            line = br.readLine();
        }
        br.close();
    }

}
