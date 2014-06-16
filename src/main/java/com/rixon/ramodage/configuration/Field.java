package com.rixon.ramodage.configuration;

/**
* This class represents a field structure that is the basis for generating random values. This class provides all the
* constraints in the boundary of which the random values needs to be generated.
* User: rixonmathew
* Date: 19/01/13
* Time: 2:30 PM
*/
public class Field<TYPE> {
    private String name;
    private String type;
    private int minLength;
    private int maxLength;
    private int fixedLength;
    private String formatMask;
    private String range;
    private TYPE suffix;
    private TYPE prefix;
    private String minValue;
    private String maxValue;
    private String stepValue;
    private String padding;
    private String constantValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getFormatMask() {
        return formatMask;
    }

    public void setFormatMask(String formatMask) {
        this.formatMask = formatMask;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(int fixedLength) {
        this.fixedLength = fixedLength;
    }

    public TYPE getSuffix() {
        return suffix;
    }

    public void setSuffix(TYPE suffix) {
        this.suffix = suffix;
    }

    public TYPE getPrefix() {
        return prefix;
    }

    public void setPrefix(TYPE prefix) {
        this.prefix = prefix;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    public String getStepValue() {
        return stepValue;
    }

    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", fixedLength=" + fixedLength +
                ", formatMask='" + formatMask + '\'' +
                ", range='" + range + '\'' +
                ", suffix=" + suffix +
                ", prefix=" + prefix +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", stepValue='" + stepValue + '\'' +
                ", padding='" + padding + '\'' +
                ", constantValue='" + constantValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (!name.equals(field.name)) return false;
        if (!type.equals(field.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
