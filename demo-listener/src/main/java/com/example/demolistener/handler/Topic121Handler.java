package com.example.demolistener.handler;

import com.example.demolistener.pojos.TestPojo;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.camunda.spin.Spin.JSON;

@Component
@ExternalTaskSubscription("topic121") // create a subscription for this topic name
public class Topic121Handler implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // get variables

        String value_1 = externalTask.getVariable("value_1");
        String value_2 = externalTask.getVariable("value_2");

        Logger.getLogger("topic121").log(Level.INFO, "Received variables value_1 {0} and value_2 {1}", new Object[]{value_1, value_2});

        /*//prepare some varibles to send
        String customerId = "C-" + UUID.randomUUID().toString().substring(32);
        int creditScore = 50;

        VariableMap variablesToSend = Variables.createVariables();
        variablesToSend.put("customerId", customerId);
        variablesToSend.put("creditScore", creditScore);*/

        TestPojo pojo1 = new TestPojo();
        pojo1.setId(1);
        pojo1.setName("This is one");
        pojo1.setValue("value11");
        pojo1.setLocalName("this is local name 11");


        TestPojo pojo2 = new TestPojo();
        pojo2.setId(2);
        pojo2.setName("This is Two");
        pojo2.setValue("value22");
        pojo2.setLocalName("this is local name 22");


        List<TestPojo> myPojoList = new ArrayList<>();
        myPojoList.add(pojo1);
        myPojoList.add(pojo2);



        String myJsonDataToSend = JSON(myPojoList).toString();

        Map<String,Object> myResults = new HashMap<>();
        myResults.put("myData",myJsonDataToSend);



        // complete the external task
        externalTaskService.complete(externalTask, myResults);

        Logger.getLogger("topic121").log(Level.INFO, "sent variable myResult {0} ", myResults);

    }
}