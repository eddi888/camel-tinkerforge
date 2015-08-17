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
    public void asyncFileRead(int file_id, short error_code, short[] buffer, short length_read) {
        LOG.trace("asyncFileRead()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_ASYNC_FILE_READ);
            exchange.getIn().setHeader("file_id", file_id);
            exchange.getIn().setHeader("error_code", error_code);
            exchange.getIn().setHeader("buffer", buffer);
            exchange.getIn().setHeader("length_read", length_read);
            
            
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
    public void asyncFileWrite(int file_id, short error_code, short length_written) {
        LOG.trace("asyncFileWrite()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_ASYNC_FILE_WRITE);
            exchange.getIn().setHeader("file_id", file_id);
            exchange.getIn().setHeader("error_code", error_code);
            exchange.getIn().setHeader("length_written", length_written);
            
            
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
    public void fileEventsOccurred(int file_id, int events) {
        LOG.trace("fileEventsOccurred()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_FILE_EVENTS_OCCURRED);
            exchange.getIn().setHeader("file_id", file_id);
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
    public void processStateChanged(int process_id, short state, long timestamp, short exit_code) {
        LOG.trace("processStateChanged()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROCESS_STATE_CHANGED);
            exchange.getIn().setHeader("process_id", process_id);
            exchange.getIn().setHeader("state", state);
            exchange.getIn().setHeader("timestamp", timestamp);
            exchange.getIn().setHeader("exit_code", exit_code);
            
            
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
    public void programSchedulerStateChanged(int program_id) {
        LOG.trace("programSchedulerStateChanged()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROGRAM_SCHEDULER_STATE_CHANGED);
            exchange.getIn().setHeader("program_id", program_id);
            
            
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
    public void programProcessSpawned(int program_id) {
        LOG.trace("programProcessSpawned()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickRED.CALLBACK_PROGRAM_PROCESS_SPAWNED);
            exchange.getIn().setHeader("program_id", program_id);
            
            
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