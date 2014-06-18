This utility can be used to generate structured data sets using random value providers. Some applications of this utility
are generate large amounts of trade or quotes data. This utility can scale to provide GB's of data in minutes
(27 GB data has been generated in under 30 minutes on a laptop(i5 with 4GB ram running Ubuntu 12.04))

How to use this
================
1) Standalone client
=====================
a) Build the jar:
   This utility is completely mavenized so jar can be built using command
   ```
   mvn clean compile assembly:single
   ```

b) Create the schema:
   Based on the data set required the schema file needs to be created.Many sample schema files
   are provided in test/resources folder

c) Create the properties file:
   This file is used to provide other properties for the utility

d) Run the utility:
   ```
   java -jar ramodage-1.0-jar-with-dependencies.jar --file <property_file>
   ```

   The utility will display the progress while its running ( This is especially useful in case of generating large amounts of data)
   Once this is done it will create a folder specified in the properties file and all files with data will be generated
   there

2) Library
=======================
Ramodage can be used as a library in your java projects to generate random data either in files or in memory. For in memory data generation
the caller need only to pass the schema definition and a class in which the results will be held.

a) Install the ramdoage library to you local repository
    mvn clean compile test install

b) Include it in your required projects ( versions may vary. Please update accordingly)
```
        <dependency>
            <groupId>com.rixon.ramodage</groupId>
            <artifactId>ramodage</artifactId>
            <version>${ramodage.version}</version>
        </dependency>
```
c) Here is a small test (LibTest.java) that demonstrates how to use this in synchronous manner.
```java
    @Test
    public void testAPIBehaviour() {
       Properties properties = createProperties();
       RandomData<DailyTrade> randomData = ramodage.generateData(properties);
       assertNotNull(randomData);
       List<DailyTrade> allRecords = randomData.getAllRecords();
       assertNotNull(allRecords);
       int expectedSize=expectedRecords*expectedSplits;
       assertThat("Size is not as expected",allRecords.size(),is(expectedSize));
       for (int i=0;i<100;i++) {
           DailyTrade dailyTrade = randomData.getRandomRecord();
           assertNotNull(dailyTrade);
       }
    }

```

d) And here is snippet for asynchronous call
```java
    @Test
    public void testAPIBehaviourForAsyncCall() {
        Properties properties = createProperties();
        long startTime = System.currentTimeMillis();
        DataGenerationStatus<DailyTrade> dataGenerationStatus = ramodage.generateDataAsynchronously(properties);

        long timeTakenToReturnAsyncCall=System.currentTimeMillis()-startTime;
        System.out.println("timeTakenToReturnAsyncCall = " + timeTakenToReturnAsyncCall);
        assertNotNull(dataGenerationStatus);
        while (!dataGenerationStatus.isDataGenerationComplete()) {
            try {
                Thread.sleep(500l);
            } catch (InterruptedException e) {}
        }
        long timeTaken=System.currentTimeMillis()-startTime;
        System.out.println("timeTaken = " + timeTaken);
        RandomData<DailyTrade> randomData = dataGenerationStatus.getRandomData();
        List<DailyTrade> allRecords = randomData.getAllRecords();
        assertNotNull(allRecords);
        int expectedSize=expectedRecords*expectedSplits;
        assertThat("Size is not as expected",allRecords.size(),is(expectedSize));
        for (int i=0;i<100;i++) {
            DailyTrade dailyTrade = randomData.getRandomRecord();
            assertNotNull(dailyTrade);
        }

    }
```

The schema for both these test cases can be found in file daily_trades.json under test/resources