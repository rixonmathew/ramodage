package com.rixon.ramodage.strategy.marketdata;

import com.rixon.ramodage.configuration.Field;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 7/3/13
 * Time: 6:20 PM
 * This file is used for generating volume data for quotes. This file
 */
public class QuoteDataGenerationStrategy extends AbstractMarketDataGenerationStrategy {

    @Override
    protected Map<Field, String> determineOverriddenValues(Long startTime, String symbolName, PriceSet priceSet) {
        Map<Field,String> overriddenValues = new HashMap<>();
        Field symbolField = fieldMap.get(SYMBOL);
        overriddenValues.put(symbolField,symbolName);
        Field timestampField = fieldMap.get(TIMESTAMP);
        String timeStamp = getValueWithPadding(Long.toString(startTime),timestampField);
        overriddenValues.put(timestampField,timeStamp);
        String BID_PRICE = "BidPrice";
        Field bidPrice = fieldMap.get(BID_PRICE);
        double randomBoundForPrice = 20.0d;
        double randomBoundForQuantity = 50.0d;
        String bidPriceValue = getRandomValue(priceSet.bidPrice, randomBoundForPrice,bidPrice);
        overriddenValues.put(bidPrice,bidPriceValue);
        String BID_SIZE = "BidSize";
        Field bidQuantity = fieldMap.get(BID_SIZE);
        String bidQuantityValue = getRandomValue(priceSet.bidQuantity, randomBoundForQuantity,bidQuantity);
        overriddenValues.put(bidQuantity,bidQuantityValue);
        String ASK_PRICE = "AskPrice";
        Field askPrice = fieldMap.get(ASK_PRICE);
        String askPriceValue = getRandomValue(priceSet.askPrice, randomBoundForPrice,askPrice);
        overriddenValues.put(askPrice,askPriceValue);
        String ASK_SIZE = "AskSize";
        Field askQuantity = fieldMap.get(ASK_SIZE);
        String askQuantityValue = getRandomValue(priceSet.askQuantity, randomBoundForQuantity,askQuantity);
        overriddenValues.put(askQuantity,askQuantityValue);
        return overriddenValues;
    }
}
