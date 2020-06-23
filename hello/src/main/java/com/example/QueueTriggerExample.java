package com.example;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;

public class QueueTriggerExample extends AzureSpringBootRequestHandler<Object, Object> {

	@FunctionName("queueProcessor")
	public void queueProcessorFunction(@QueueTrigger(name = "msg", queueName = "trigger", connection = "AzureWebJobsStorage")
		String message, final ExecutionContext context) {

		context.getLogger().info(message);
	}
}
