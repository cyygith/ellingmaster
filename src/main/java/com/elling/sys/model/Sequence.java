package com.elling.sys.model;

import javax.persistence.*;

public class Sequence {
    @Id
    @Column(name = "sequence_name")
    private String sequenceName;

    @Column(name = "current_value")
    private Long currentValue;

    private Integer increment;

    @Column(name = "max_value")
    private Long maxValue;

    @Column(name = "initial_value")
    private Long initialValue;

    /**
     * @return sequence_name
     */
    public String getSequenceName() {
        return sequenceName;
    }

    /**
     * @param sequenceName
     */
    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    /**
     * @return current_value
     */
    public Long getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue
     */
    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * @return increment
     */
    public Integer getIncrement() {
        return increment;
    }

    /**
     * @param increment
     */
    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    /**
     * @return max_value
     */
    public Long getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue
     */
    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return initial_value
     */
    public Long getInitialValue() {
        return initialValue;
    }

    /**
     * @param initialValue
     */
    public void setInitialValue(Long initialValue) {
        this.initialValue = initialValue;
    }
}