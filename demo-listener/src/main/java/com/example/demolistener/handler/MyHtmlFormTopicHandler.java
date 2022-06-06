package com.example.demolistener.handler;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.client.variable.ClientValues;
import org.camunda.bpm.client.variable.value.JsonValue;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.spin.Spin;
import org.camunda.spin.json.SpinJsonNode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("my-hml-form-test1-topic") // create a subscription for this topic name
public class MyHtmlFormTopicHandler implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // get variables

        String yourName = externalTask.getVariable("yourName");

        Logger.getLogger("my-hml-form-test1-topic").log(Level.INFO, "Received variables yourName {0}", new Object[]{yourName});

        /*//prepare some varibles to send
        String customerId = "C-" + UUID.randomUUID().toString().substring(32);
        int creditScore = 50;

        VariableMap variablesToSend = Variables.createVariables();
        variablesToSend.put("customerId", customerId);
        variablesToSend.put("creditScore", creditScore);*/

        /*TestPojo pojo1 = new TestPojo();
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
        myPojoList.add(pojo2);*/



        /*String myJsonDataToSend = JSON(myPojoList).toString();

        Map<String,Object> myResults = new HashMap<>();
        myResults.put("myData",myJsonDataToSend);*/

        Map<String, String> slaSelectValues = new HashMap<String, String>();
        slaSelectValues.put("001", "Advanced");
        slaSelectValues.put("002", "Premium");
        slaSelectValues.put("003", "Standard");


//        Map<String,Object> myResults = new HashMap<>();
//        myResults.put("myDataValues",Spin.JSON(slaSelectValues));

        VariableMap variables = Variables.createVariables();
        JsonValue myDataValues = ClientValues.jsonValue(Spin.JSON(slaSelectValues).toString());
        variables.put("myDataValues", myDataValues);

        // complete the external task
        externalTaskService.complete(externalTask, variables);


        // complete the external task
        //externalTaskService.complete(externalTask, myResults);

        //Logger.getLogger("my-hml-form-test1-topic").log(Level.INFO, "sent variable myResult {0} ", myResults);
        Logger.getLogger("my-hml-form-test1-topic").log(Level.INFO, "sent variable myResult {0} ", variables);

    }
}