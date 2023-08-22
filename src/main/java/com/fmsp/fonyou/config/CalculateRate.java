package com.fmsp.fonyou.config;

import org.springframework.stereotype.Service;

@Service
public class CalculateRate {
    public static double calculateRate(int questionCount) {
        return 100.0/questionCount;
    }
}
