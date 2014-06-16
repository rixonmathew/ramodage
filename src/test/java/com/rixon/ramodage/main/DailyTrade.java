package com.rixon.ramodage.main;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 1:46 AM
 */
public class DailyTrade {

    private Date timestamp;
    private String exchange;
    private String symbol;
    private String saleCondition;
    private BigDecimal tradeVolume;
    private BigDecimal tradePrice;
    private String tradeStopStockIndicator;
    private String tradeCorectionIndicator;
    private BigDecimal tradeSequenceNumber;
    private String tradeSource;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSaleCondition() {
        return saleCondition;
    }

    public void setSaleCondition(String saleCondition) {
        this.saleCondition = saleCondition;
    }

    public BigDecimal getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(BigDecimal tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeStopStockIndicator() {
        return tradeStopStockIndicator;
    }

    public void setTradeStopStockIndicator(String tradeStopStockIndicator) {
        this.tradeStopStockIndicator = tradeStopStockIndicator;
    }

    public String getTradeCorectionIndicator() {
        return tradeCorectionIndicator;
    }

    public void setTradeCorectionIndicator(String tradeCorectionIndicator) {
        this.tradeCorectionIndicator = tradeCorectionIndicator;
    }

    public BigDecimal getTradeSequenceNumber() {
        return tradeSequenceNumber;
    }

    public void setTradeSequenceNumber(BigDecimal tradeSequenceNumber) {
        this.tradeSequenceNumber = tradeSequenceNumber;
    }

    public String getTradeSource() {
        return tradeSource;
    }

    public void setTradeSource(String tradeSource) {
        this.tradeSource = tradeSource;
    }

    @Override
    public String toString() {
        return "DailyTrade{" +
                "timestamp=" + timestamp +
                ", exchange='" + exchange + '\'' +
                ", symbol='" + symbol + '\'' +
                ", saleCondition='" + saleCondition + '\'' +
                ", tradeVolume=" + tradeVolume +
                ", tradePrice=" + tradePrice +
                ", tradeStopStockIndicator='" + tradeStopStockIndicator + '\'' +
                ", tradeCorectionIndicator='" + tradeCorectionIndicator + '\'' +
                ", tradeSequenceNumber=" + tradeSequenceNumber +
                ", tradeSource='" + tradeSource + '\'' +
                '}';
    }
}
