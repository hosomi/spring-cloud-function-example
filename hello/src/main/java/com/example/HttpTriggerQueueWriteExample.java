package com.example;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.QueueOutput;

public class HttpTriggerQueueWriteExample  extends AzureSpringBootRequestHandler<Object, Object> {

	@FunctionName("receiveQueueFunction")
	public HttpResponseMessage receiveQueueFunction(
	        @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION)
	        HttpRequestMessage<Optional<String>> request,
	        @QueueOutput(name = "msg", queueName = "example", connection = "AzureWebJobsStorage")
	        OutputBinding<String> msg, final ExecutionContext context) {

		context.getLogger().info("Java HTTP trigger processed a request.");

		String query = request.getQueryParameters().get("name");
		String name = request.getBody().orElse(query);
	    if (name == null) {
	        return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
	        .body("Please pass a name on the query string or in the request body").build();
	    } else {
	        msg.setValue(name);
	        return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
	    }
	}

}



