package be.nrb.o2;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

@ProcessApplication
public class StringLimitApplication extends ServletProcessApplication {

  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {

    //Map<String, Object> variables = new HashMap<>();
    //variables.put("name", "John");
    
    //processEngine.getRuntimeService().startProcessInstanceByKey("Process", variables);
  }

}
