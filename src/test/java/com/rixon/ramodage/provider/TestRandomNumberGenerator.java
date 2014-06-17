package com.rixon.ramodage.provider;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertNotNull;

/**
 * User: rixonmathew
 * Date: 20/01/13
 * Time: 9:58 PM
 */
public class TestRandomNumberGenerator {

    @Test
    public void testSimpleRandomValues() {
       Random random = new Random();
       random.setSeed(100l);
       for(int i=0;i<1000;i++){
           int nextRandom = random.nextInt(1000);
           assertNotNull(nextRandom);
           //System.out.println("random = " + random.nextInt(1000));

       }
    }
}
