package com.example;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

public class FunctionRequestHandler extends AzureSpringBootRequestHandler<Object, Object> {

    @FunctionName("scheduleMinutesFunction")
    public void scheduleMinutesFunction(@TimerTrigger(name = "timer", schedule = "0 */1 * * * *") String timerInfo,
            final ExecutionContext context) {

        context.getLogger().info("scheduleMinutesFunction!");
    }
}