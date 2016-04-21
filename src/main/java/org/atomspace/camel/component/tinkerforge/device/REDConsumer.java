/**
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.atomspace.camel.component.tinkerforge.device;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.atomspace.camel.component.tinkerforge.TinkerforgeConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickRED;

import com.tinkerforge.BrickRED.AsyncFileReadListener;
import com.tinkerforge.BrickRED.AsyncFileWriteListener;
import com.tinkerforge.BrickRED.FileEventsOccurredListener;
import com.tinkerforge.BrickRED.ProcessStateChangedListener;
import com.tinkerforge.BrickRED.ProgramSchedulerStateChangedListener;
import com.tinkerforge.BrickRED.ProgramProcessSpawnedListener;;

public class REDConsumer extends TinkerforgeConsumer<REDEndpoint, BrickRED> implements AsyncFileReadListener, AsyncFileWriteListener, FileEventsOccurredListener, ProcessStateChangedListener, ProgramSchedulerStateChangedListener, ProgramProcessSpawnedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(REDConsumer.class);
    
    public REDConsumer(REDEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickRED(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addAsyncFileReadListener(this);
            device.addAsyncFileWriteListener(this);
            device.addFileEventsOccurredListener(this);
            device.addProcessStateChangedListener(this);
            device.addProgramSchedulerStateChangedListener(this);
            device.addProgramProcessSpawnedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("AsyncFileReadListener")) device.addAsyncFileReadListener(this);
                if(callback.equals("AsyncFileWriteListener")) device.addAsyncFileWriteListener(this);
                if(callback.equals("FileEventsOccurredListener")) device.addFileEventsOccurredListener(this);
                if(callback.equals("ProcessStateChangedListener")) device.addProcessStateChangedListener(this);
                if(callback.equals("ProgramSchedulerStateChangedListener")) device.addProgramSchedulerStateChangedListener(this);
                if(callback.equals("ProgramProcessSpawnedListener")) device.addProgramProcessSpawnedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void asyncFileRead(int fileId, short errorCode, short[] buffer, short lengthRead) {
        LOG.trace("asyncFileRead()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_ASYNC_FILE_READ);
            exchange.getIn().setHeader("fileId", fileId);
            exchange.getIn().setHeader("errorCode", errorCode);
            exchange.getIn().setHeader("buffer", buffer);
            exchange.getIn().setHeader("lengthRead", lengthRead);
            
            
            // ADD BODY
            exchange.getIn().setBody("async_file_read");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void asyncFileWrite(int fileId, short errorCode, short lengthWritten) {
        LOG.trace("asyncFileWrite()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_ASYNC_FILE_WRITE);
            exchange.getIn().setHeader("fileId", fileId);
            exchange.getIn().setHeader("errorCode", errorCode);
            exchange.getIn().setHeader("lengthWritten", lengthWritten);
            
            
            // ADD BODY
            exchange.getIn().setBody("async_file_write");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void fileEventsOccurred(int fileId, int events) {
        LOG.trace("fileEventsOccurred()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_FILE_EVENTS_OCCURRED);
            exchange.getIn().setHeader("fileId", fileId);
            exchange.getIn().setHeader("events", events);
            
            
            // ADD BODY
            exchange.getIn().setBody("file_events_occurred");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void processStateChanged(int processId, short state, long timestamp, short exitCode) {
        LOG.trace("processStateChanged()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROCESS_STATE_CHANGED);
            exchange.getIn().setHeader("processId", processId);
            exchange.getIn().setHeader("state", state);
            exchange.getIn().setHeader("timestamp", timestamp);
            exchange.getIn().setHeader("exitCode", exitCode);
            
            
            // ADD BODY
            exchange.getIn().setBody("process_state_changed");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void programSchedulerStateChanged(int programId) {
        LOG.trace("programSchedulerStateChanged()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROGRAM_SCHEDULER_STATE_CHANGED);
            exchange.getIn().setHeader("programId", programId);
            
            
            // ADD BODY
            exchange.getIn().setBody("program_scheduler_state_changed");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void programProcessSpawned(int programId) {
        LOG.trace("programProcessSpawned()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROGRAM_PROCESS_SPAWNED);
            exchange.getIn().setHeader("programId", programId);
            
            
            // ADD BODY
            exchange.getIn().setBody("program_process_spawned");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    

}