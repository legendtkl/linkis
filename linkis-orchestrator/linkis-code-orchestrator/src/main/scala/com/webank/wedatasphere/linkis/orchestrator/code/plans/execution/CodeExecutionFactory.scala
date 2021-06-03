/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.orchestrator.code.plans.execution

import com.webank.wedatasphere.linkis.orchestrator.core.SessionState
import com.webank.wedatasphere.linkis.orchestrator.execution.impl.AbstractExecutionFactory
import com.webank.wedatasphere.linkis.orchestrator.execution.{TaskConsumer, TaskManager}
import com.webank.wedatasphere.linkis.orchestrator.listener.OrchestratorListenerBusContext
import com.webank.wedatasphere.linkis.orchestrator.strategy.async.AsyncTaskManager

class CodeExecutionFactory extends AbstractExecutionFactory{

  override protected def getTaskConsumer(sessionState: SessionState): TaskConsumer = {
    val taskConsumer = new CodeReheaterNotifyTaskConsumer(sessionState.getReheater)
    //OrchestratorListenerBusContext.getListenerBusContext().getOrchestratorAsyncListenerBus.addListener(taskConsumer)
    sessionState.getOrchestratorAsyncListenerBus.addListener(taskConsumer)
    taskConsumer
  }

  override protected def getTaskManager(): TaskManager = {
    val taskManager = new AsyncTaskManager
    //OrchestratorListenerBusContext.getListenerBusContext().getOrchestratorSyncListenerBus.addListener(taskManager)
    taskManager
  }

}
