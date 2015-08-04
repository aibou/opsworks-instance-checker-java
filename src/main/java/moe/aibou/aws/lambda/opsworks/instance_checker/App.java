package moe.aibou.aws.lambda.opsworks.instance_checker;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.opsworks.AWSOpsWorksClient;
import com.amazonaws.services.opsworks.model.DescribeInstancesRequest;
import com.amazonaws.services.opsworks.model.DescribeInstancesResult;
import com.amazonaws.services.opsworks.model.DescribeStacksRequest;
import com.amazonaws.services.opsworks.model.DescribeStacksResult;

/**
 * Hello world!
 *
 */
public class App {
    public String handler(String json, Context context) {
        AWSOpsWorksClient opsWorksClient = new AWSOpsWorksClient();
        DescribeStacksRequest stacksRequest = new DescribeStacksRequest();
        DescribeStacksResult stacksResult = opsWorksClient.describeStacks(stacksRequest);
        stacksResult.getStacks().forEach(stack -> {
            DescribeInstancesRequest instancesRequest = new DescribeInstancesRequest();
            instancesRequest.setStackId(stack.getStackId());
            DescribeInstancesResult instancesResult = opsWorksClient.describeInstances(instancesRequest);
            instancesResult.getInstances().forEach(instance -> {
                if(instance.getStatus().contains("failed")) {
                    System.out.println(instance.getHostname());
                }
            });

        });
        return "";
    }
}
