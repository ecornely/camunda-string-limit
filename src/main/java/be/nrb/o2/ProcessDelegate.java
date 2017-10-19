package be.nrb.o2;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String processDefinitionId = execution.getProcessInstance().getProcessDefinitionId();
    ProcessDefinition definition = execution.getProcessEngineServices().getRepositoryService().getProcessDefinition(processDefinitionId);
    LoggerFactory.getLogger(this.getClass()).debug("A process : {} is running ...", definition.getKey());
    
    String stringLimit = (String) execution.getVariable("stringLimit");
    if(stringLimit!=null && !stringLimit.isEmpty()) {
      LoggerFactory.getLogger(this.getClass()).info("Received a String of length {} with content: {}", stringLimit.length(), stringLimit);
    }
    
  }

}
