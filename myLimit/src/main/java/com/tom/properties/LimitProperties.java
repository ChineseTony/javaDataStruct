package com.tom.properties;


import com.tom.enums.LimitType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.tom.limit")
public class LimitProperties {

    private String name;

    private String key;

    private String prefix;

    private int period;

    private int count;

    private LimitType limitType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LimitType getLimitType() {
        return limitType;
    }

    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }

    @Override
    public String toString() {
        return "LimitProperties{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", prefix='" + prefix + '\'' +
                ", period=" + period +
                ", count=" + count +
                ", limitType=" + limitType +
                '}';
    }
}
