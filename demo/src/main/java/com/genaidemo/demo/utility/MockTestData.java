package com.genaidemo.demo.utility;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.config.EnvironmentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Slf4j
public class MockTestData {
@LazyAutowired
private EnvironmentProperties environmentProperties;

    public int randomNumberGenerator(int a, int b){
        int i = 0;
        return i;
    }

    public Map<String,String> getLoginDetail(String userType){
        Map<String,String> logInVal = new HashMap<>();
        if (userType.equalsIgnoreCase("User")){
            logInVal.put("userId",this.environmentProperties.getUserId());
            logInVal.put("password",this.environmentProperties.getPassword());
        }
        return logInVal;
    }

    public String getZipCode(){
        Set<String> zipCodes = new HashSet<>();
        Random rand = new Random();
        zipCodes.add("00926");
        zipCodes.add("11368");
        zipCodes.add("60629");
        zipCodes.add("79936");
        zipCodes.add("90011");
        zipCodes.add("90650");
        zipCodes.add("91331");
        zipCodes.add("99950");
        zipCodes.add("01009");
        zipCodes.add("11234");
        zipCodes.add("11230");
        zipCodes.add("01238");
        zipCodes.add("11233");
        zipCodes.add("22701");
        zipCodes.add("03227");
        zipCodes.add("15227");
        zipCodes.add("04069");
        zipCodes.add("04691");
        zipCodes.add("01069");
        zipCodes.add("03269");
        zipCodes.add("02816");

        int i = rand.nextInt(zipCodes.size());
        String[] zipArray = zipCodes.toArray(new String[0]);
        return zipArray[i];

    }

}
