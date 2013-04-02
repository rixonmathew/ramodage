package com.ramodage.strategy.marketdata;

import com.ramodage.configuration.Field;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 11/3/13
 * Time: 4:31 PM
 * This class represents the strategy used for populating the Trades record.
 * Rather than pricing the trades completely randomly the percentage variation can be controlled
 * to generate variation within a bound
 * */
public class TradesGenerationStrategy extends AbstractMarketDataGenerationStrategy {

    private final static String TRADE_VOLUME= "TradeVolume";
    private final static String TRADE_PRICE= "TradePrice";

    @Override
    protected Map<Field, String> determineOverriddenValues(Long startTime, String symbolName, PriceSet priceSet) {
        Map<Field,String> overriddenValues = new HashMap<Field, String>();
        Field symbolField = fieldMap.get(SYMBOL);
        overriddenValues.put(symbolField,symbolName);
        Field timestampField = fieldMap.get(TIMESTAMP);
        String timeStamp = getValueWithPadding(Long.toString(startTime),timestampField);
        overriddenValues.put(timestampField,timeStamp);
        Field tradePrice = fieldMap.get(TRADE_PRICE);
        double randomBoundForPrice = 25.0d;
        double randomBoundForQuantity = 50.0d;
        String bidPriceValue = getRandomValue(priceSet.bidPrice, randomBoundForPrice,tradePrice);
        overriddenValues.put(tradePrice,bidPriceValue);
        Field tradeVolume = fieldMap.get(TRADE_VOLUME);
        String bidQuantityValue = getRandomValue(priceSet.bidQuantity, randomBoundForQuantity,tradeVolume);
        overriddenValues.put(tradeVolume,bidQuantityValue);
        return overriddenValues;
    }
}
