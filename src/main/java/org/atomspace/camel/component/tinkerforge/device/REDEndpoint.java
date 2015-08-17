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

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickRED;

/**
 * Executes user programs and controls other Bricks/Bricklets standalone
 */
public class REDEndpoint extends TinkerforgeEndpoint<REDConsumer, REDProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(REDEndpoint.class);
    
    private Long lifetime;
    private Integer sessionId;
    private Integer sessionId2;
    private Integer sessionId3;
    private Long lifetime2;
    private Integer objectId;
    private Integer sessionId4;
    private Integer objectId2;
    private Integer sessionId5;
    private Long lengthToReserve;
    private String buffer;
    private Integer sessionId6;
    private Integer stringId;
    private Long length;
    private Integer stringId2;
    private Integer stringId3;
    private Long offset;
    private String buffer2;
    private Integer stringId4;
    private Long offset2;
    private Integer lengthToReserve2;
    private Integer sessionId7;
    private Integer listId;
    private Integer listId2;
    private Integer index;
    private Integer sessionId8;
    private Integer listId3;
    private Integer itemObjectId;
    private Integer listId4;
    private Integer index2;
    private Integer nameStringId;
    private Long flags;
    private Integer permissions;
    private Long uid2;
    private Long gid;
    private Integer sessionId9;
    private Long flags2;
    private Long length2;
    private Integer sessionId10;
    private Integer fileId;
    private Integer sessionId11;
    private Integer fileId2;
    private Short lengthToRead;
    private Integer fileId3;
    private Long lengthToRead2;
    private Integer fileId4;
    private Integer fileId5;
    private short[] buffer3;
    private Short lengthToWrite;
    private Integer fileId6;
    private short[] buffer4;
    private Short lengthToWrite2;
    private Integer fileId7;
    private short[] buffer5;
    private Short lengthToWrite3;
    private Integer fileId8;
    private Long offset3;
    private Short origin;
    private Integer fileId9;
    private Integer fileId10;
    private Integer events;
    private Integer fileId11;
    private Integer nameStringId2;
    private Integer sessionId12;
    private Integer directoryId;
    private Integer sessionId13;
    private Integer directoryId2;
    private Integer sessionId14;
    private Integer directoryId3;
    private Integer nameStringId3;
    private Long flags3;
    private Integer permissions2;
    private Long uid3;
    private Long gid2;
    private Integer sessionId15;
    private Integer executableStringId;
    private Integer argumentsListId;
    private Integer environmentListId;
    private Integer workingDirectoryStringId;
    private Long uid4;
    private Long gid3;
    private Integer stdinFileId;
    private Integer stdoutFileId;
    private Integer stderrFileId;
    private Integer sessionId16;
    private Integer processId;
    private Short signal;
    private Integer processId2;
    private Integer sessionId17;
    private Integer processId3;
    private Integer processId4;
    private Integer sessionId18;
    private Integer processId5;
    private Integer sessionId19;
    private Integer identifierStringId;
    private Integer sessionId20;
    private Integer programId;
    private Long cookie;
    private Integer programId2;
    private Integer sessionId21;
    private Integer programId3;
    private Integer sessionId22;
    private Integer programId4;
    private Integer executableStringId2;
    private Integer argumentsListId2;
    private Integer environmentListId2;
    private Integer workingDirectoryStringId2;
    private Integer programId5;
    private Integer sessionId23;
    private Integer programId6;
    private Short stdinRedirection;
    private Integer stdinFileNameStringId;
    private Short stdoutRedirection;
    private Integer stdoutFileNameStringId;
    private Short stderrRedirection;
    private Integer stderrFileNameStringId;
    private Integer programId7;
    private Integer sessionId24;
    private Integer programId8;
    private Short startMode;
    private Boolean continueAfterError;
    private Long startInterval;
    private Integer startFieldsStringId;
    private Integer programId9;
    private Integer sessionId25;
    private Integer programId10;
    private Integer sessionId26;
    private Integer programId11;
    private Integer programId12;
    private Integer programId13;
    private Integer sessionId27;
    private Integer programId14;
    private Integer sessionId28;
    private Integer programId15;
    private Integer nameStringId4;
    private Integer valueStringId;
    private Integer programId16;
    private Integer nameStringId5;
    private Integer sessionId29;
    private Integer programId17;
    private Integer nameStringId6;

        
    public REDEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new REDProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new REDConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickRED device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickRED device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "createSession":
                response = device.createSession(
                        getValue(long.class, "lifetime", m, getLifetime())
                    );
                break;

            case "expireSession":
                response = device.expireSession(
                        getValue(int.class, "sessionId", m, getSessionId())
                    );
                break;

            case "expireSessionUnchecked":
                device.expireSessionUnchecked(
                        getValue(int.class, "sessionId2", m, getSessionId2())
                    );
                break;

            case "keepSessionAlive":
                response = device.keepSessionAlive(
                        getValue(int.class, "sessionId3", m, getSessionId3()),
                        getValue(long.class, "lifetime2", m, getLifetime2())
                    );
                break;

            case "releaseObject":
                response = device.releaseObject(
                        getValue(int.class, "objectId", m, getObjectId()),
                        getValue(int.class, "sessionId4", m, getSessionId4())
                    );
                break;

            case "releaseObjectUnchecked":
                device.releaseObjectUnchecked(
                        getValue(int.class, "objectId2", m, getObjectId2()),
                        getValue(int.class, "sessionId5", m, getSessionId5())
                    );
                break;

            case "allocateString":
                response = device.allocateString(
                        getValue(long.class, "lengthToReserve", m, getLengthToReserve()),
                        getValue(String.class, "buffer", m, getBuffer()),
                        getValue(int.class, "sessionId6", m, getSessionId6())
                    );
                break;

            case "truncateString":
                response = device.truncateString(
                        getValue(int.class, "stringId", m, getStringId()),
                        getValue(long.class, "length", m, getLength())
                    );
                break;

            case "getStringLength":
                response = device.getStringLength(
                        getValue(int.class, "stringId2", m, getStringId2())
                    );
                break;

            case "setStringChunk":
                response = device.setStringChunk(
                        getValue(int.class, "stringId3", m, getStringId3()),
                        getValue(long.class, "offset", m, getOffset()),
                        getValue(String.class, "buffer2", m, getBuffer2())
                    );
                break;

            case "getStringChunk":
                response = device.getStringChunk(
                        getValue(int.class, "stringId4", m, getStringId4()),
                        getValue(long.class, "offset2", m, getOffset2())
                    );
                break;

            case "allocateList":
                response = device.allocateList(
                        getValue(int.class, "lengthToReserve2", m, getLengthToReserve2()),
                        getValue(int.class, "sessionId7", m, getSessionId7())
                    );
                break;

            case "getListLength":
                response = device.getListLength(
                        getValue(int.class, "listId", m, getListId())
                    );
                break;

            case "getListItem":
                response = device.getListItem(
                        getValue(int.class, "listId2", m, getListId2()),
                        getValue(int.class, "index", m, getIndex()),
                        getValue(int.class, "sessionId8", m, getSessionId8())
                    );
                break;

            case "appendToList":
                response = device.appendToList(
                        getValue(int.class, "listId3", m, getListId3()),
                        getValue(int.class, "itemObjectId", m, getItemObjectId())
                    );
                break;

            case "removeFromList":
                response = device.removeFromList(
                        getValue(int.class, "listId4", m, getListId4()),
                        getValue(int.class, "index2", m, getIndex2())
                    );
                break;

            case "openFile":
                response = device.openFile(
                        getValue(int.class, "nameStringId", m, getNameStringId()),
                        getValue(long.class, "flags", m, getFlags()),
                        getValue(int.class, "permissions", m, getPermissions()),
                        getValue(long.class, "uid2", m, getUid2()),
                        getValue(long.class, "gid", m, getGid()),
                        getValue(int.class, "sessionId9", m, getSessionId9())
                    );
                break;

            case "createPipe":
                response = device.createPipe(
                        getValue(long.class, "flags2", m, getFlags2()),
                        getValue(long.class, "length2", m, getLength2()),
                        getValue(int.class, "sessionId10", m, getSessionId10())
                    );
                break;

            case "getFileInfo":
                response = device.getFileInfo(
                        getValue(int.class, "fileId", m, getFileId()),
                        getValue(int.class, "sessionId11", m, getSessionId11())
                    );
                break;

            case "readFile":
                response = device.readFile(
                        getValue(int.class, "fileId2", m, getFileId2()),
                        getValue(short.class, "lengthToRead", m, getLengthToRead())
                    );
                break;

            case "readFileAsync":
                device.readFileAsync(
                        getValue(int.class, "fileId3", m, getFileId3()),
                        getValue(long.class, "lengthToRead2", m, getLengthToRead2())
                    );
                break;

            case "abortAsyncFileRead":
                response = device.abortAsyncFileRead(
                        getValue(int.class, "fileId4", m, getFileId4())
                    );
                break;

            case "writeFile":
                response = device.writeFile(
                        getValue(int.class, "fileId5", m, getFileId5()),
                        getValue(short[].class, "buffer3", m, getBuffer3()),
                        getValue(short.class, "lengthToWrite", m, getLengthToWrite())
                    );
                break;

            case "writeFileUnchecked":
                device.writeFileUnchecked(
                        getValue(int.class, "fileId6", m, getFileId6()),
                        getValue(short[].class, "buffer4", m, getBuffer4()),
                        getValue(short.class, "lengthToWrite2", m, getLengthToWrite2())
                    );
                break;

            case "writeFileAsync":
                device.writeFileAsync(
                        getValue(int.class, "fileId7", m, getFileId7()),
                        getValue(short[].class, "buffer5", m, getBuffer5()),
                        getValue(short.class, "lengthToWrite3", m, getLengthToWrite3())
                    );
                break;

            case "setFilePosition":
                response = device.setFilePosition(
                        getValue(int.class, "fileId8", m, getFileId8()),
                        getValue(long.class, "offset3", m, getOffset3()),
                        getValue(short.class, "origin", m, getOrigin())
                    );
                break;

            case "getFilePosition":
                response = device.getFilePosition(
                        getValue(int.class, "fileId9", m, getFileId9())
                    );
                break;

            case "setFileEvents":
                response = device.setFileEvents(
                        getValue(int.class, "fileId10", m, getFileId10()),
                        getValue(int.class, "events", m, getEvents())
                    );
                break;

            case "getFileEvents":
                response = device.getFileEvents(
                        getValue(int.class, "fileId11", m, getFileId11())
                    );
                break;

            case "openDirectory":
                response = device.openDirectory(
                        getValue(int.class, "nameStringId2", m, getNameStringId2()),
                        getValue(int.class, "sessionId12", m, getSessionId12())
                    );
                break;

            case "getDirectoryName":
                response = device.getDirectoryName(
                        getValue(int.class, "directoryId", m, getDirectoryId()),
                        getValue(int.class, "sessionId13", m, getSessionId13())
                    );
                break;

            case "getNextDirectoryEntry":
                response = device.getNextDirectoryEntry(
                        getValue(int.class, "directoryId2", m, getDirectoryId2()),
                        getValue(int.class, "sessionId14", m, getSessionId14())
                    );
                break;

            case "rewindDirectory":
                response = device.rewindDirectory(
                        getValue(int.class, "directoryId3", m, getDirectoryId3())
                    );
                break;

            case "createDirectory":
                response = device.createDirectory(
                        getValue(int.class, "nameStringId3", m, getNameStringId3()),
                        getValue(long.class, "flags3", m, getFlags3()),
                        getValue(int.class, "permissions2", m, getPermissions2()),
                        getValue(long.class, "uid3", m, getUid3()),
                        getValue(long.class, "gid2", m, getGid2())
                    );
                break;

            case "getProcesses":
                response = device.getProcesses(
                        getValue(int.class, "sessionId15", m, getSessionId15())
                    );
                break;

            case "spawnProcess":
                response = device.spawnProcess(
                        getValue(int.class, "executableStringId", m, getExecutableStringId()),
                        getValue(int.class, "argumentsListId", m, getArgumentsListId()),
                        getValue(int.class, "environmentListId", m, getEnvironmentListId()),
                        getValue(int.class, "workingDirectoryStringId", m, getWorkingDirectoryStringId()),
                        getValue(long.class, "uid4", m, getUid4()),
                        getValue(long.class, "gid3", m, getGid3()),
                        getValue(int.class, "stdinFileId", m, getStdinFileId()),
                        getValue(int.class, "stdoutFileId", m, getStdoutFileId()),
                        getValue(int.class, "stderrFileId", m, getStderrFileId()),
                        getValue(int.class, "sessionId16", m, getSessionId16())
                    );
                break;

            case "killProcess":
                response = device.killProcess(
                        getValue(int.class, "processId", m, getProcessId()),
                        getValue(short.class, "signal", m, getSignal())
                    );
                break;

            case "getProcessCommand":
                response = device.getProcessCommand(
                        getValue(int.class, "processId2", m, getProcessId2()),
                        getValue(int.class, "sessionId17", m, getSessionId17())
                    );
                break;

            case "getProcessIdentity":
                response = device.getProcessIdentity(
                        getValue(int.class, "processId3", m, getProcessId3())
                    );
                break;

            case "getProcessStdio":
                response = device.getProcessStdio(
                        getValue(int.class, "processId4", m, getProcessId4()),
                        getValue(int.class, "sessionId18", m, getSessionId18())
                    );
                break;

            case "getProcessState":
                response = device.getProcessState(
                        getValue(int.class, "processId5", m, getProcessId5())
                    );
                break;

            case "getPrograms":
                response = device.getPrograms(
                        getValue(int.class, "sessionId19", m, getSessionId19())
                    );
                break;

            case "defineProgram":
                response = device.defineProgram(
                        getValue(int.class, "identifierStringId", m, getIdentifierStringId()),
                        getValue(int.class, "sessionId20", m, getSessionId20())
                    );
                break;

            case "purgeProgram":
                response = device.purgeProgram(
                        getValue(int.class, "programId", m, getProgramId()),
                        getValue(long.class, "cookie", m, getCookie())
                    );
                break;

            case "getProgramIdentifier":
                response = device.getProgramIdentifier(
                        getValue(int.class, "programId2", m, getProgramId2()),
                        getValue(int.class, "sessionId21", m, getSessionId21())
                    );
                break;

            case "getProgramRootDirectory":
                response = device.getProgramRootDirectory(
                        getValue(int.class, "programId3", m, getProgramId3()),
                        getValue(int.class, "sessionId22", m, getSessionId22())
                    );
                break;

            case "setProgramCommand":
                response = device.setProgramCommand(
                        getValue(int.class, "programId4", m, getProgramId4()),
                        getValue(int.class, "executableStringId2", m, getExecutableStringId2()),
                        getValue(int.class, "argumentsListId2", m, getArgumentsListId2()),
                        getValue(int.class, "environmentListId2", m, getEnvironmentListId2()),
                        getValue(int.class, "workingDirectoryStringId2", m, getWorkingDirectoryStringId2())
                    );
                break;

            case "getProgramCommand":
                response = device.getProgramCommand(
                        getValue(int.class, "programId5", m, getProgramId5()),
                        getValue(int.class, "sessionId23", m, getSessionId23())
                    );
                break;

            case "setProgramStdioRedirection":
                response = device.setProgramStdioRedirection(
                        getValue(int.class, "programId6", m, getProgramId6()),
                        getValue(short.class, "stdinRedirection", m, getStdinRedirection()),
                        getValue(int.class, "stdinFileNameStringId", m, getStdinFileNameStringId()),
                        getValue(short.class, "stdoutRedirection", m, getStdoutRedirection()),
                        getValue(int.class, "stdoutFileNameStringId", m, getStdoutFileNameStringId()),
                        getValue(short.class, "stderrRedirection", m, getStderrRedirection()),
                        getValue(int.class, "stderrFileNameStringId", m, getStderrFileNameStringId())
                    );
                break;

            case "getProgramStdioRedirection":
                response = device.getProgramStdioRedirection(
                        getValue(int.class, "programId7", m, getProgramId7()),
                        getValue(int.class, "sessionId24", m, getSessionId24())
                    );
                break;

            case "setProgramSchedule":
                response = device.setProgramSchedule(
                        getValue(int.class, "programId8", m, getProgramId8()),
                        getValue(short.class, "startMode", m, getStartMode()),
                        getValue(boolean.class, "continueAfterError", m, getContinueAfterError()),
                        getValue(long.class, "startInterval", m, getStartInterval()),
                        getValue(int.class, "startFieldsStringId", m, getStartFieldsStringId())
                    );
                break;

            case "getProgramSchedule":
                response = device.getProgramSchedule(
                        getValue(int.class, "programId9", m, getProgramId9()),
                        getValue(int.class, "sessionId25", m, getSessionId25())
                    );
                break;

            case "getProgramSchedulerState":
                response = device.getProgramSchedulerState(
                        getValue(int.class, "programId10", m, getProgramId10()),
                        getValue(int.class, "sessionId26", m, getSessionId26())
                    );
                break;

            case "continueProgramSchedule":
                response = device.continueProgramSchedule(
                        getValue(int.class, "programId11", m, getProgramId11())
                    );
                break;

            case "startProgram":
                response = device.startProgram(
                        getValue(int.class, "programId12", m, getProgramId12())
                    );
                break;

            case "getLastSpawnedProgramProcess":
                response = device.getLastSpawnedProgramProcess(
                        getValue(int.class, "programId13", m, getProgramId13()),
                        getValue(int.class, "sessionId27", m, getSessionId27())
                    );
                break;

            case "getCustomProgramOptionNames":
                response = device.getCustomProgramOptionNames(
                        getValue(int.class, "programId14", m, getProgramId14()),
                        getValue(int.class, "sessionId28", m, getSessionId28())
                    );
                break;

            case "setCustomProgramOptionValue":
                response = device.setCustomProgramOptionValue(
                        getValue(int.class, "programId15", m, getProgramId15()),
                        getValue(int.class, "nameStringId4", m, getNameStringId4()),
                        getValue(int.class, "valueStringId", m, getValueStringId())
                    );
                break;

            case "getCustomProgramOptionValue":
                response = device.getCustomProgramOptionValue(
                        getValue(int.class, "programId16", m, getProgramId16()),
                        getValue(int.class, "nameStringId5", m, getNameStringId5()),
                        getValue(int.class, "sessionId29", m, getSessionId29())
                    );
                break;

            case "removeCustomProgramOption":
                response = device.removeCustomProgramOption(
                        getValue(int.class, "programId17", m, getProgramId17()),
                        getValue(int.class, "nameStringId6", m, getNameStringId6())
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * 
     */
    public Long getLifetime(){
        return lifetime;
    }

    public void setLifetime(Long lifetime){
        this.lifetime = lifetime;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId(){
        return sessionId;
    }

    public void setSessionId(Integer sessionId){
        this.sessionId = sessionId;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId2(){
        return sessionId2;
    }

    public void setSessionId2(Integer sessionId2){
        this.sessionId2 = sessionId2;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId3(){
        return sessionId3;
    }

    public void setSessionId3(Integer sessionId3){
        this.sessionId3 = sessionId3;
    }

    /**
     * 
     * 
     */
    public Long getLifetime2(){
        return lifetime2;
    }

    public void setLifetime2(Long lifetime2){
        this.lifetime2 = lifetime2;
    }

    /**
     * 
     * Decreases the reference count of an object by one and returns the resulting
     * error code. If the reference count reaches zero the object gets destroyed.
     * 
     */
    public Integer getObjectId(){
        return objectId;
    }

    public void setObjectId(Integer objectId){
        this.objectId = objectId;
    }

    /**
     * 
     * Decreases the reference count of an object by one and returns the resulting
     * error code. If the reference count reaches zero the object gets destroyed.
     * 
     */
    public Integer getSessionId4(){
        return sessionId4;
    }

    public void setSessionId4(Integer sessionId4){
        this.sessionId4 = sessionId4;
    }

    /**
     * 
     * 
     */
    public Integer getObjectId2(){
        return objectId2;
    }

    public void setObjectId2(Integer objectId2){
        this.objectId2 = objectId2;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId5(){
        return sessionId5;
    }

    public void setSessionId5(Integer sessionId5){
        this.sessionId5 = sessionId5;
    }

    /**
     * 
     * Allocates a new string object, reserves ``length_to_reserve`` bytes memory
     * for it and sets up to the first 60 bytes. Set ``length_to_reserve`` to the
     * length of the string that should be stored in the string object.
     * 
     * Returns the object ID of the new string object and the resulting error code.
     * 
     */
    public Long getLengthToReserve(){
        return lengthToReserve;
    }

    public void setLengthToReserve(Long lengthToReserve){
        this.lengthToReserve = lengthToReserve;
    }

    /**
     * 
     * Allocates a new string object, reserves ``length_to_reserve`` bytes memory
     * for it and sets up to the first 60 bytes. Set ``length_to_reserve`` to the
     * length of the string that should be stored in the string object.
     * 
     * Returns the object ID of the new string object and the resulting error code.
     * 
     */
    public String getBuffer(){
        return buffer;
    }

    public void setBuffer(String buffer){
        this.buffer = buffer;
    }

    /**
     * 
     * Allocates a new string object, reserves ``length_to_reserve`` bytes memory
     * for it and sets up to the first 60 bytes. Set ``length_to_reserve`` to the
     * length of the string that should be stored in the string object.
     * 
     * Returns the object ID of the new string object and the resulting error code.
     * 
     */
    public Integer getSessionId6(){
        return sessionId6;
    }

    public void setSessionId6(Integer sessionId6){
        this.sessionId6 = sessionId6;
    }

    /**
     * 
     * Truncates a string object to ``length`` bytes and returns the resulting
     * error code.
     * 
     */
    public Integer getStringId(){
        return stringId;
    }

    public void setStringId(Integer stringId){
        this.stringId = stringId;
    }

    /**
     * 
     * Truncates a string object to ``length`` bytes and returns the resulting
     * error code.
     * 
     */
    public Long getLength(){
        return length;
    }

    public void setLength(Long length){
        this.length = length;
    }

    /**
     * 
     * Returns the length of a string object in bytes and the resulting error code.
     * 
     */
    public Integer getStringId2(){
        return stringId2;
    }

    public void setStringId2(Integer stringId2){
        this.stringId2 = stringId2;
    }

    /**
     * 
     * Sets a chunk of up to 58 bytes in a string object beginning at ``offset``.
     * 
     * Returns the resulting error code.
     * 
     */
    public Integer getStringId3(){
        return stringId3;
    }

    public void setStringId3(Integer stringId3){
        this.stringId3 = stringId3;
    }

    /**
     * 
     * Sets a chunk of up to 58 bytes in a string object beginning at ``offset``.
     * 
     * Returns the resulting error code.
     * 
     */
    public Long getOffset(){
        return offset;
    }

    public void setOffset(Long offset){
        this.offset = offset;
    }

    /**
     * 
     * Sets a chunk of up to 58 bytes in a string object beginning at ``offset``.
     * 
     * Returns the resulting error code.
     * 
     */
    public String getBuffer2(){
        return buffer2;
    }

    public void setBuffer2(String buffer2){
        this.buffer2 = buffer2;
    }

    /**
     * 
     * Returns a chunk up to 63 bytes from a string object beginning at ``offset`` and
     * returns the resulting error code.
     * 
     */
    public Integer getStringId4(){
        return stringId4;
    }

    public void setStringId4(Integer stringId4){
        this.stringId4 = stringId4;
    }

    /**
     * 
     * Returns a chunk up to 63 bytes from a string object beginning at ``offset`` and
     * returns the resulting error code.
     * 
     */
    public Long getOffset2(){
        return offset2;
    }

    public void setOffset2(Long offset2){
        this.offset2 = offset2;
    }

    /**
     * 
     * Allocates a new list object and reserves memory for ``length_to_reserve``
     * items. Set ``length_to_reserve`` to the number of items that should be stored
     * in the list object.
     * 
     * Returns the object ID of the new list object and the resulting error code.
     * 
     * When a list object gets destroyed then the reference count of each object in
     * the list object is decreased by one.
     * 
     */
    public Integer getLengthToReserve2(){
        return lengthToReserve2;
    }

    public void setLengthToReserve2(Integer lengthToReserve2){
        this.lengthToReserve2 = lengthToReserve2;
    }

    /**
     * 
     * Allocates a new list object and reserves memory for ``length_to_reserve``
     * items. Set ``length_to_reserve`` to the number of items that should be stored
     * in the list object.
     * 
     * Returns the object ID of the new list object and the resulting error code.
     * 
     * When a list object gets destroyed then the reference count of each object in
     * the list object is decreased by one.
     * 
     */
    public Integer getSessionId7(){
        return sessionId7;
    }

    public void setSessionId7(Integer sessionId7){
        this.sessionId7 = sessionId7;
    }

    /**
     * 
     * Returns the length of a list object in items and the resulting error code.
     * 
     */
    public Integer getListId(){
        return listId;
    }

    public void setListId(Integer listId){
        this.listId = listId;
    }

    /**
     * 
     * Returns the object ID and type of the object stored at ``index`` in a list
     * object and returns the resulting error code.
     * 
     * Possible object types are:
     * 
     * * String = 0
     * * List = 1
     * * File = 2
     * * Directory = 3
     * * Process = 4
     * * Program = 5
     * 
     */
    public Integer getListId2(){
        return listId2;
    }

    public void setListId2(Integer listId2){
        this.listId2 = listId2;
    }

    /**
     * 
     * Returns the object ID and type of the object stored at ``index`` in a list
     * object and returns the resulting error code.
     * 
     * Possible object types are:
     * 
     * * String = 0
     * * List = 1
     * * File = 2
     * * Directory = 3
     * * Process = 4
     * * Program = 5
     * 
     */
    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    /**
     * 
     * Returns the object ID and type of the object stored at ``index`` in a list
     * object and returns the resulting error code.
     * 
     * Possible object types are:
     * 
     * * String = 0
     * * List = 1
     * * File = 2
     * * Directory = 3
     * * Process = 4
     * * Program = 5
     * 
     */
    public Integer getSessionId8(){
        return sessionId8;
    }

    public void setSessionId8(Integer sessionId8){
        this.sessionId8 = sessionId8;
    }

    /**
     * 
     * Appends an object to a list object and increases the reference count of the
     * appended object by one.
     * 
     * Returns the resulting error code.
     * 
     */
    public Integer getListId3(){
        return listId3;
    }

    public void setListId3(Integer listId3){
        this.listId3 = listId3;
    }

    /**
     * 
     * Appends an object to a list object and increases the reference count of the
     * appended object by one.
     * 
     * Returns the resulting error code.
     * 
     */
    public Integer getItemObjectId(){
        return itemObjectId;
    }

    public void setItemObjectId(Integer itemObjectId){
        this.itemObjectId = itemObjectId;
    }

    /**
     * 
     * Removes the object stored at ``index`` from a list object and decreases the
     * reference count of the removed object by one.
     * 
     * Returns the resulting error code.
     * 
     */
    public Integer getListId4(){
        return listId4;
    }

    public void setListId4(Integer listId4){
        this.listId4 = listId4;
    }

    /**
     * 
     * Removes the object stored at ``index`` from a list object and decreases the
     * reference count of the removed object by one.
     * 
     * Returns the resulting error code.
     * 
     */
    public Integer getIndex2(){
        return index2;
    }

    public void setIndex2(Integer index2){
        this.index2 = index2;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Integer getNameStringId(){
        return nameStringId;
    }

    public void setNameStringId(Integer nameStringId){
        this.nameStringId = nameStringId;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Long getFlags(){
        return flags;
    }

    public void setFlags(Long flags){
        this.flags = flags;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Integer getPermissions(){
        return permissions;
    }

    public void setPermissions(Integer permissions){
        this.permissions = permissions;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Long getUid2(){
        return uid2;
    }

    public void setUid2(Long uid2){
        this.uid2 = uid2;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Long getGid(){
        return gid;
    }

    public void setGid(Long gid){
        this.gid = gid;
    }

    /**
     * 
     * Opens an existing file or creates a new file and allocates a new file object
     * for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * file object gets destroyed then the reference count of the name string object is
     * decreased by one. Also the name string object is locked and cannot be modified
     * while the file object holds a reference to it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible file
     * flags (in hexadecimal notation):
     * 
     * * ReadOnly = 0x0001 (O_RDONLY)
     * * WriteOnly = 0x0002 (O_WRONLY)
     * * ReadWrite = 0x0004 (O_RDWR)
     * * Append = 0x0008 (O_APPEND)
     * * Create = 0x0010 (O_CREAT)
     * * Exclusive = 0x0020 (O_EXCL)
     * * NonBlocking = 0x0040 (O_NONBLOCK)
     * * Truncate = 0x0080 (O_TRUNC)
     * * Temporary = 0x0100
     * * Replace = 0x0200
     * 
     * FIXME: explain *Temporary* and *Replace* flag
     * 
     * The ``permissions`` parameter takes a ORed combination of the following
     * possible file permissions (in octal notation) that match the common UNIX
     * permission bits:
     * 
     * * UserRead = 00400
     * * UserWrite = 00200
     * * UserExecute = 00100
     * * GroupRead = 00040
     * * GroupWrite = 00020
     * * GroupExecute = 00010
     * * OthersRead = 00004
     * * OthersWrite = 00002
     * * OthersExecute = 00001
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Integer getSessionId9(){
        return sessionId9;
    }

    public void setSessionId9(Integer sessionId9){
        this.sessionId9 = sessionId9;
    }

    /**
     * 
     * Creates a new pipe and allocates a new file object for it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible
     * pipe flags (in hexadecimal notation):
     * 
     * * NonBlockingRead = 0x0001
     * * NonBlockingWrite = 0x0002
     * 
     * The length of the pipe buffer can be specified with the ``length`` parameter
     * in bytes. If length is set to zero, then the default pipe buffer length is used.
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Long getFlags2(){
        return flags2;
    }

    public void setFlags2(Long flags2){
        this.flags2 = flags2;
    }

    /**
     * 
     * Creates a new pipe and allocates a new file object for it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible
     * pipe flags (in hexadecimal notation):
     * 
     * * NonBlockingRead = 0x0001
     * * NonBlockingWrite = 0x0002
     * 
     * The length of the pipe buffer can be specified with the ``length`` parameter
     * in bytes. If length is set to zero, then the default pipe buffer length is used.
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Long getLength2(){
        return length2;
    }

    public void setLength2(Long length2){
        this.length2 = length2;
    }

    /**
     * 
     * Creates a new pipe and allocates a new file object for it.
     * 
     * The ``flags`` parameter takes a ORed combination of the following possible
     * pipe flags (in hexadecimal notation):
     * 
     * * NonBlockingRead = 0x0001
     * * NonBlockingWrite = 0x0002
     * 
     * The length of the pipe buffer can be specified with the ``length`` parameter
     * in bytes. If length is set to zero, then the default pipe buffer length is used.
     * 
     * Returns the object ID of the new file object and the resulting error code.
     * 
     */
    public Integer getSessionId10(){
        return sessionId10;
    }

    public void setSessionId10(Integer sessionId10){
        this.sessionId10 = sessionId10;
    }

    /**
     * 
     * Returns various information about a file and the resulting error code.
     * 
     * Possible file types are:
     * 
     * * Unknown = 0
     * * Regular = 1
     * * Directory = 2
     * * Character = 3
     * * Block = 4
     * * FIFO = 5
     * * Symlink = 6
     * * Socket = 7
     * * Pipe = 8
     * 
     * If the file type is *Pipe* then the returned name string object is invalid,
     * because a pipe has no name. Otherwise the returned name string object was used
     * to open or create the file object, as passed to :func:`OpenFile`.
     * 
     * The returned flags were used to open or create the file object, as passed to
     * :func:`OpenFile` or :func:`CreatePipe`. See the respective function for a list
     * of possible file and pipe flags.
     * 
     * FIXME: everything except flags and length is invalid if file type is *Pipe*
     * 
     */
    public Integer getFileId(){
        return fileId;
    }

    public void setFileId(Integer fileId){
        this.fileId = fileId;
    }

    /**
     * 
     * Returns various information about a file and the resulting error code.
     * 
     * Possible file types are:
     * 
     * * Unknown = 0
     * * Regular = 1
     * * Directory = 2
     * * Character = 3
     * * Block = 4
     * * FIFO = 5
     * * Symlink = 6
     * * Socket = 7
     * * Pipe = 8
     * 
     * If the file type is *Pipe* then the returned name string object is invalid,
     * because a pipe has no name. Otherwise the returned name string object was used
     * to open or create the file object, as passed to :func:`OpenFile`.
     * 
     * The returned flags were used to open or create the file object, as passed to
     * :func:`OpenFile` or :func:`CreatePipe`. See the respective function for a list
     * of possible file and pipe flags.
     * 
     * FIXME: everything except flags and length is invalid if file type is *Pipe*
     * 
     */
    public Integer getSessionId11(){
        return sessionId11;
    }

    public void setSessionId11(Integer sessionId11){
        this.sessionId11 = sessionId11;
    }

    /**
     * 
     * Reads up to 62 bytes from a file object.
     * 
     * Returns the bytes read, the actual number of bytes read and the resulting
     * error code.
     * 
     * If there is not data to be read, either because the file position reached
     * end-of-file or because there is not data in the pipe, then zero bytes are
     * returned.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingRead* flag then the
     * error code *NotSupported* is returned.
     * 
     */
    public Integer getFileId2(){
        return fileId2;
    }

    public void setFileId2(Integer fileId2){
        this.fileId2 = fileId2;
    }

    /**
     * 
     * Reads up to 62 bytes from a file object.
     * 
     * Returns the bytes read, the actual number of bytes read and the resulting
     * error code.
     * 
     * If there is not data to be read, either because the file position reached
     * end-of-file or because there is not data in the pipe, then zero bytes are
     * returned.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingRead* flag then the
     * error code *NotSupported* is returned.
     * 
     */
    public Short getLengthToRead(){
        return lengthToRead;
    }

    public void setLengthToRead(Short lengthToRead){
        this.lengthToRead = lengthToRead;
    }

    /**
     * 
     * Reads up to 2\ :sup:`63`\  - 1 bytes from a file object asynchronously.
     * 
     * Reports the bytes read (in 60 byte chunks), the actual number of bytes read and
     * the resulting error code via the :func:`AsyncFileRead` callback.
     * 
     * If there is not data to be read, either because the file position reached
     * end-of-file or because there is not data in the pipe, then zero bytes are
     * reported.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingRead* flag then the error
     * code *NotSupported* is reported via the :func:`AsyncFileRead` callback.
     * 
     */
    public Integer getFileId3(){
        return fileId3;
    }

    public void setFileId3(Integer fileId3){
        this.fileId3 = fileId3;
    }

    /**
     * 
     * Reads up to 2\ :sup:`63`\  - 1 bytes from a file object asynchronously.
     * 
     * Reports the bytes read (in 60 byte chunks), the actual number of bytes read and
     * the resulting error code via the :func:`AsyncFileRead` callback.
     * 
     * If there is not data to be read, either because the file position reached
     * end-of-file or because there is not data in the pipe, then zero bytes are
     * reported.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingRead* flag then the error
     * code *NotSupported* is reported via the :func:`AsyncFileRead` callback.
     * 
     */
    public Long getLengthToRead2(){
        return lengthToRead2;
    }

    public void setLengthToRead2(Long lengthToRead2){
        this.lengthToRead2 = lengthToRead2;
    }

    /**
     * 
     * Aborts a :func:`ReadFileAsync` operation in progress.
     * 
     * Returns the resulting error code.
     * 
     * On success the :func:`AsyncFileRead` callback will report *OperationAborted*.
     * 
     */
    public Integer getFileId4(){
        return fileId4;
    }

    public void setFileId4(Integer fileId4){
        this.fileId4 = fileId4;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Returns the actual number of bytes written and the resulting error code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is returned.
     * 
     */
    public Integer getFileId5(){
        return fileId5;
    }

    public void setFileId5(Integer fileId5){
        this.fileId5 = fileId5;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Returns the actual number of bytes written and the resulting error code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is returned.
     * 
     */
    public short[] getBuffer3(){
        return buffer3;
    }

    public void setBuffer3(short[] buffer3){
        this.buffer3 = buffer3;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Returns the actual number of bytes written and the resulting error code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is returned.
     * 
     */
    public Short getLengthToWrite(){
        return lengthToWrite;
    }

    public void setLengthToWrite(Short lengthToWrite){
        this.lengthToWrite = lengthToWrite;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Does neither report the actual number of bytes written nor the resulting error
     * code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * write operation will fail silently.
     * 
     */
    public Integer getFileId6(){
        return fileId6;
    }

    public void setFileId6(Integer fileId6){
        this.fileId6 = fileId6;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Does neither report the actual number of bytes written nor the resulting error
     * code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * write operation will fail silently.
     * 
     */
    public short[] getBuffer4(){
        return buffer4;
    }

    public void setBuffer4(short[] buffer4){
        this.buffer4 = buffer4;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Does neither report the actual number of bytes written nor the resulting error
     * code.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * write operation will fail silently.
     * 
     */
    public Short getLengthToWrite2(){
        return lengthToWrite2;
    }

    public void setLengthToWrite2(Short lengthToWrite2){
        this.lengthToWrite2 = lengthToWrite2;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Reports the actual number of bytes written and the resulting error code via the
     * :func:`AsyncFileWrite` callback.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is reported via the :func:`AsyncFileWrite` callback.
     * 
     */
    public Integer getFileId7(){
        return fileId7;
    }

    public void setFileId7(Integer fileId7){
        this.fileId7 = fileId7;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Reports the actual number of bytes written and the resulting error code via the
     * :func:`AsyncFileWrite` callback.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is reported via the :func:`AsyncFileWrite` callback.
     * 
     */
    public short[] getBuffer5(){
        return buffer5;
    }

    public void setBuffer5(short[] buffer5){
        this.buffer5 = buffer5;
    }

    /**
     * 
     * Writes up to 61 bytes to a file object.
     * 
     * Reports the actual number of bytes written and the resulting error code via the
     * :func:`AsyncFileWrite` callback.
     * 
     * If the file object was created by :func:`OpenFile` without the *NonBlocking*
     * flag or by :func:`CreatePipe` without the *NonBlockingWrite* flag then the
     * error code *NotSupported* is reported via the :func:`AsyncFileWrite` callback.
     * 
     */
    public Short getLengthToWrite3(){
        return lengthToWrite3;
    }

    public void setLengthToWrite3(Short lengthToWrite3){
        this.lengthToWrite3 = lengthToWrite3;
    }

    /**
     * 
     * Set the current seek position of a file object in bytes relative to ``origin``.
     * 
     * Possible file origins are:
     * 
     * * Beginning = 0
     * * Current = 1
     * * End = 2
     * 
     * Returns the resulting absolute seek position and error code.
     * 
     * If the file object was created by :func:`CreatePipe` then it has no seek
     * position and the error code *InvalidSeek* is returned.
     * 
     */
    public Integer getFileId8(){
        return fileId8;
    }

    public void setFileId8(Integer fileId8){
        this.fileId8 = fileId8;
    }

    /**
     * 
     * Set the current seek position of a file object in bytes relative to ``origin``.
     * 
     * Possible file origins are:
     * 
     * * Beginning = 0
     * * Current = 1
     * * End = 2
     * 
     * Returns the resulting absolute seek position and error code.
     * 
     * If the file object was created by :func:`CreatePipe` then it has no seek
     * position and the error code *InvalidSeek* is returned.
     * 
     */
    public Long getOffset3(){
        return offset3;
    }

    public void setOffset3(Long offset3){
        this.offset3 = offset3;
    }

    /**
     * 
     * Set the current seek position of a file object in bytes relative to ``origin``.
     * 
     * Possible file origins are:
     * 
     * * Beginning = 0
     * * Current = 1
     * * End = 2
     * 
     * Returns the resulting absolute seek position and error code.
     * 
     * If the file object was created by :func:`CreatePipe` then it has no seek
     * position and the error code *InvalidSeek* is returned.
     * 
     */
    public Short getOrigin(){
        return origin;
    }

    public void setOrigin(Short origin){
        this.origin = origin;
    }

    /**
     * 
     * Returns the current seek position of a file object in bytes and returns the
     * resulting error code.
     * 
     * If the file object was created by :func:`CreatePipe` then it has no seek
     * position and the error code *InvalidSeek* is returned.
     * 
     */
    public Integer getFileId9(){
        return fileId9;
    }

    public void setFileId9(Integer fileId9){
        this.fileId9 = fileId9;
    }

    /**
     * 
     * 
     */
    public Integer getFileId10(){
        return fileId10;
    }

    public void setFileId10(Integer fileId10){
        this.fileId10 = fileId10;
    }

    /**
     * 
     * 
     */
    public Integer getEvents(){
        return events;
    }

    public void setEvents(Integer events){
        this.events = events;
    }

    /**
     * 
     * 
     */
    public Integer getFileId11(){
        return fileId11;
    }

    public void setFileId11(Integer fileId11){
        this.fileId11 = fileId11;
    }

    /**
     * 
     * Opens an existing directory and allocates a new directory object for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * directory object is destroyed then the reference count of the name string
     * object is decreased by one. Also the name string object is locked and cannot be
     * modified while the directory object holds a reference to it.
     * 
     * Returns the object ID of the new directory object and the resulting error code.
     * 
     */
    public Integer getNameStringId2(){
        return nameStringId2;
    }

    public void setNameStringId2(Integer nameStringId2){
        this.nameStringId2 = nameStringId2;
    }

    /**
     * 
     * Opens an existing directory and allocates a new directory object for it.
     * 
     * FIXME: name has to be absolute
     * 
     * The reference count of the name string object is increased by one. When the
     * directory object is destroyed then the reference count of the name string
     * object is decreased by one. Also the name string object is locked and cannot be
     * modified while the directory object holds a reference to it.
     * 
     * Returns the object ID of the new directory object and the resulting error code.
     * 
     */
    public Integer getSessionId12(){
        return sessionId12;
    }

    public void setSessionId12(Integer sessionId12){
        this.sessionId12 = sessionId12;
    }

    /**
     * 
     * Returns the name of a directory object, as passed to :func:`OpenDirectory`, and
     * the resulting error code.
     * 
     */
    public Integer getDirectoryId(){
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId){
        this.directoryId = directoryId;
    }

    /**
     * 
     * Returns the name of a directory object, as passed to :func:`OpenDirectory`, and
     * the resulting error code.
     * 
     */
    public Integer getSessionId13(){
        return sessionId13;
    }

    public void setSessionId13(Integer sessionId13){
        this.sessionId13 = sessionId13;
    }

    /**
     * 
     * Returns the next entry in a directory object and the resulting error code.
     * 
     * If there is not next entry then error code *NoMoreData* is returned. To rewind
     * a directory object call :func:`RewindDirectory`.
     * 
     * Possible directory entry types are:
     * 
     * * Unknown = 0
     * * Regular = 1
     * * Directory = 2
     * * Character = 3
     * * Block = 4
     * * FIFO = 5
     * * Symlink = 6
     * * Socket = 7
     * 
     */
    public Integer getDirectoryId2(){
        return directoryId2;
    }

    public void setDirectoryId2(Integer directoryId2){
        this.directoryId2 = directoryId2;
    }

    /**
     * 
     * Returns the next entry in a directory object and the resulting error code.
     * 
     * If there is not next entry then error code *NoMoreData* is returned. To rewind
     * a directory object call :func:`RewindDirectory`.
     * 
     * Possible directory entry types are:
     * 
     * * Unknown = 0
     * * Regular = 1
     * * Directory = 2
     * * Character = 3
     * * Block = 4
     * * FIFO = 5
     * * Symlink = 6
     * * Socket = 7
     * 
     */
    public Integer getSessionId14(){
        return sessionId14;
    }

    public void setSessionId14(Integer sessionId14){
        this.sessionId14 = sessionId14;
    }

    /**
     * 
     * Rewinds a directory object and returns the resulting error code.
     * 
     */
    public Integer getDirectoryId3(){
        return directoryId3;
    }

    public void setDirectoryId3(Integer directoryId3){
        this.directoryId3 = directoryId3;
    }

    /**
     * 
     * FIXME: name has to be absolute
     * 
     */
    public Integer getNameStringId3(){
        return nameStringId3;
    }

    public void setNameStringId3(Integer nameStringId3){
        this.nameStringId3 = nameStringId3;
    }

    /**
     * 
     * FIXME: name has to be absolute
     * 
     */
    public Long getFlags3(){
        return flags3;
    }

    public void setFlags3(Long flags3){
        this.flags3 = flags3;
    }

    /**
     * 
     * FIXME: name has to be absolute
     * 
     */
    public Integer getPermissions2(){
        return permissions2;
    }

    public void setPermissions2(Integer permissions2){
        this.permissions2 = permissions2;
    }

    /**
     * 
     * FIXME: name has to be absolute
     * 
     */
    public Long getUid3(){
        return uid3;
    }

    public void setUid3(Long uid3){
        this.uid3 = uid3;
    }

    /**
     * 
     * FIXME: name has to be absolute
     * 
     */
    public Long getGid2(){
        return gid2;
    }

    public void setGid2(Long gid2){
        this.gid2 = gid2;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId15(){
        return sessionId15;
    }

    public void setSessionId15(Integer sessionId15){
        this.sessionId15 = sessionId15;
    }

    /**
     * 
     * 
     */
    public Integer getExecutableStringId(){
        return executableStringId;
    }

    public void setExecutableStringId(Integer executableStringId){
        this.executableStringId = executableStringId;
    }

    /**
     * 
     * 
     */
    public Integer getArgumentsListId(){
        return argumentsListId;
    }

    public void setArgumentsListId(Integer argumentsListId){
        this.argumentsListId = argumentsListId;
    }

    /**
     * 
     * 
     */
    public Integer getEnvironmentListId(){
        return environmentListId;
    }

    public void setEnvironmentListId(Integer environmentListId){
        this.environmentListId = environmentListId;
    }

    /**
     * 
     * 
     */
    public Integer getWorkingDirectoryStringId(){
        return workingDirectoryStringId;
    }

    public void setWorkingDirectoryStringId(Integer workingDirectoryStringId){
        this.workingDirectoryStringId = workingDirectoryStringId;
    }

    /**
     * 
     * 
     */
    public Long getUid4(){
        return uid4;
    }

    public void setUid4(Long uid4){
        this.uid4 = uid4;
    }

    /**
     * 
     * 
     */
    public Long getGid3(){
        return gid3;
    }

    public void setGid3(Long gid3){
        this.gid3 = gid3;
    }

    /**
     * 
     * 
     */
    public Integer getStdinFileId(){
        return stdinFileId;
    }

    public void setStdinFileId(Integer stdinFileId){
        this.stdinFileId = stdinFileId;
    }

    /**
     * 
     * 
     */
    public Integer getStdoutFileId(){
        return stdoutFileId;
    }

    public void setStdoutFileId(Integer stdoutFileId){
        this.stdoutFileId = stdoutFileId;
    }

    /**
     * 
     * 
     */
    public Integer getStderrFileId(){
        return stderrFileId;
    }

    public void setStderrFileId(Integer stderrFileId){
        this.stderrFileId = stderrFileId;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId16(){
        return sessionId16;
    }

    public void setSessionId16(Integer sessionId16){
        this.sessionId16 = sessionId16;
    }

    /**
     * 
     * Sends a UNIX signal to a process object and returns the resulting error code.
     * 
     * Possible UNIX signals are:
     * 
     * * Interrupt = 2
     * * Quit = 3
     * * Abort = 6
     * * Kill = 9
     * * User1 = 10
     * * User2 = 12
     * * Terminate = 15
     * * Continue =  18
     * * Stop = 19
     * 
     */
    public Integer getProcessId(){
        return processId;
    }

    public void setProcessId(Integer processId){
        this.processId = processId;
    }

    /**
     * 
     * Sends a UNIX signal to a process object and returns the resulting error code.
     * 
     * Possible UNIX signals are:
     * 
     * * Interrupt = 2
     * * Quit = 3
     * * Abort = 6
     * * Kill = 9
     * * User1 = 10
     * * User2 = 12
     * * Terminate = 15
     * * Continue =  18
     * * Stop = 19
     * 
     */
    public Short getSignal(){
        return signal;
    }

    public void setSignal(Short signal){
        this.signal = signal;
    }

    /**
     * 
     * Returns the executable, arguments, environment and working directory used to
     * spawn a process object, as passed to :func:`SpawnProcess`, and the resulting
     * error code.
     * 
     */
    public Integer getProcessId2(){
        return processId2;
    }

    public void setProcessId2(Integer processId2){
        this.processId2 = processId2;
    }

    /**
     * 
     * Returns the executable, arguments, environment and working directory used to
     * spawn a process object, as passed to :func:`SpawnProcess`, and the resulting
     * error code.
     * 
     */
    public Integer getSessionId17(){
        return sessionId17;
    }

    public void setSessionId17(Integer sessionId17){
        this.sessionId17 = sessionId17;
    }

    /**
     * 
     * Returns the process ID and the user and group ID used to spawn a process object,
     * as passed to :func:`SpawnProcess`, and the resulting error code.
     * 
     * The process ID is only valid if the state is *Running* or *Stopped*, see
     * :func:`GetProcessState`.
     * 
     */
    public Integer getProcessId3(){
        return processId3;
    }

    public void setProcessId3(Integer processId3){
        this.processId3 = processId3;
    }

    /**
     * 
     * Returns the stdin, stdout and stderr files used to spawn a process object, as
     * passed to :func:`SpawnProcess`, and the resulting error code.
     * 
     */
    public Integer getProcessId4(){
        return processId4;
    }

    public void setProcessId4(Integer processId4){
        this.processId4 = processId4;
    }

    /**
     * 
     * Returns the stdin, stdout and stderr files used to spawn a process object, as
     * passed to :func:`SpawnProcess`, and the resulting error code.
     * 
     */
    public Integer getSessionId18(){
        return sessionId18;
    }

    public void setSessionId18(Integer sessionId18){
        this.sessionId18 = sessionId18;
    }

    /**
     * 
     * Returns the current state, timestamp and exit code of a process object, and
     * the resulting error code.
     * 
     * Possible process states are:
     * 
     * * Unknown = 0
     * * Running = 1
     * * Error = 2
     * * Exited = 3
     * * Killed = 4
     * * Stopped = 5
     * 
     * The timestamp represents the UNIX time since when the process is in its current
     * state.
     * 
     * The exit code is only valid if the state is *Error*, *Exited*, *Killed* or
     * *Stopped* and has different meanings depending on the state:
     * 
     * * Error: error code for error occurred while spawning the process (see below)
     * * Exited: exit status of the process
     * * Killed: UNIX signal number used to kill the process
     * * Stopped: UNIX signal number used to stop the process
     * 
     * Possible exit/error codes in *Error* state are:
     * 
     * * InternalError = 125
     * * CannotExecute = 126
     * * DoesNotExist = 127
     * 
     * The *CannotExecute* error can be caused by the executable being opened for
     * writing.
     * 
     */
    public Integer getProcessId5(){
        return processId5;
    }

    public void setProcessId5(Integer processId5){
        this.processId5 = processId5;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId19(){
        return sessionId19;
    }

    public void setSessionId19(Integer sessionId19){
        this.sessionId19 = sessionId19;
    }

    /**
     * 
     * 
     */
    public Integer getIdentifierStringId(){
        return identifierStringId;
    }

    public void setIdentifierStringId(Integer identifierStringId){
        this.identifierStringId = identifierStringId;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId20(){
        return sessionId20;
    }

    public void setSessionId20(Integer sessionId20){
        this.sessionId20 = sessionId20;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId(){
        return programId;
    }

    public void setProgramId(Integer programId){
        this.programId = programId;
    }

    /**
     * 
     * 
     */
    public Long getCookie(){
        return cookie;
    }

    public void setCookie(Long cookie){
        this.cookie = cookie;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId2(){
        return programId2;
    }

    public void setProgramId2(Integer programId2){
        this.programId2 = programId2;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId21(){
        return sessionId21;
    }

    public void setSessionId21(Integer sessionId21){
        this.sessionId21 = sessionId21;
    }

    /**
     * 
     * FIXME: root directory is absolute: <home>/programs/<identifier>
     * 
     */
    public Integer getProgramId3(){
        return programId3;
    }

    public void setProgramId3(Integer programId3){
        this.programId3 = programId3;
    }

    /**
     * 
     * FIXME: root directory is absolute: <home>/programs/<identifier>
     * 
     */
    public Integer getSessionId22(){
        return sessionId22;
    }

    public void setSessionId22(Integer sessionId22){
        this.sessionId22 = sessionId22;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getProgramId4(){
        return programId4;
    }

    public void setProgramId4(Integer programId4){
        this.programId4 = programId4;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getExecutableStringId2(){
        return executableStringId2;
    }

    public void setExecutableStringId2(Integer executableStringId2){
        this.executableStringId2 = executableStringId2;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getArgumentsListId2(){
        return argumentsListId2;
    }

    public void setArgumentsListId2(Integer argumentsListId2){
        this.argumentsListId2 = argumentsListId2;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getEnvironmentListId2(){
        return environmentListId2;
    }

    public void setEnvironmentListId2(Integer environmentListId2){
        this.environmentListId2 = environmentListId2;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getWorkingDirectoryStringId2(){
        return workingDirectoryStringId2;
    }

    public void setWorkingDirectoryStringId2(Integer workingDirectoryStringId2){
        this.workingDirectoryStringId2 = workingDirectoryStringId2;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getProgramId5(){
        return programId5;
    }

    public void setProgramId5(Integer programId5){
        this.programId5 = programId5;
    }

    /**
     * 
     * FIXME: working directory is relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getSessionId23(){
        return sessionId23;
    }

    public void setSessionId23(Integer sessionId23){
        this.sessionId23 = sessionId23;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getProgramId6(){
        return programId6;
    }

    public void setProgramId6(Integer programId6){
        this.programId6 = programId6;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Short getStdinRedirection(){
        return stdinRedirection;
    }

    public void setStdinRedirection(Short stdinRedirection){
        this.stdinRedirection = stdinRedirection;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getStdinFileNameStringId(){
        return stdinFileNameStringId;
    }

    public void setStdinFileNameStringId(Integer stdinFileNameStringId){
        this.stdinFileNameStringId = stdinFileNameStringId;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Short getStdoutRedirection(){
        return stdoutRedirection;
    }

    public void setStdoutRedirection(Short stdoutRedirection){
        this.stdoutRedirection = stdoutRedirection;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getStdoutFileNameStringId(){
        return stdoutFileNameStringId;
    }

    public void setStdoutFileNameStringId(Integer stdoutFileNameStringId){
        this.stdoutFileNameStringId = stdoutFileNameStringId;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Short getStderrRedirection(){
        return stderrRedirection;
    }

    public void setStderrRedirection(Short stderrRedirection){
        this.stderrRedirection = stderrRedirection;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getStderrFileNameStringId(){
        return stderrFileNameStringId;
    }

    public void setStderrFileNameStringId(Integer stderrFileNameStringId){
        this.stderrFileNameStringId = stderrFileNameStringId;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getProgramId7(){
        return programId7;
    }

    public void setProgramId7(Integer programId7){
        this.programId7 = programId7;
    }

    /**
     * 
     * FIXME: stdio file names are relative to <home>/programs/<identifier>/bin
     * 
     */
    public Integer getSessionId24(){
        return sessionId24;
    }

    public void setSessionId24(Integer sessionId24){
        this.sessionId24 = sessionId24;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId8(){
        return programId8;
    }

    public void setProgramId8(Integer programId8){
        this.programId8 = programId8;
    }

    /**
     * 
     * 
     */
    public Short getStartMode(){
        return startMode;
    }

    public void setStartMode(Short startMode){
        this.startMode = startMode;
    }

    /**
     * 
     * 
     */
    public Boolean getContinueAfterError(){
        return continueAfterError;
    }

    public void setContinueAfterError(Boolean continueAfterError){
        this.continueAfterError = continueAfterError;
    }

    /**
     * 
     * 
     */
    public Long getStartInterval(){
        return startInterval;
    }

    public void setStartInterval(Long startInterval){
        this.startInterval = startInterval;
    }

    /**
     * 
     * 
     */
    public Integer getStartFieldsStringId(){
        return startFieldsStringId;
    }

    public void setStartFieldsStringId(Integer startFieldsStringId){
        this.startFieldsStringId = startFieldsStringId;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId9(){
        return programId9;
    }

    public void setProgramId9(Integer programId9){
        this.programId9 = programId9;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId25(){
        return sessionId25;
    }

    public void setSessionId25(Integer sessionId25){
        this.sessionId25 = sessionId25;
    }

    /**
     * 
     * FIXME: message is currently valid in error-occurred state only
     * 
     */
    public Integer getProgramId10(){
        return programId10;
    }

    public void setProgramId10(Integer programId10){
        this.programId10 = programId10;
    }

    /**
     * 
     * FIXME: message is currently valid in error-occurred state only
     * 
     */
    public Integer getSessionId26(){
        return sessionId26;
    }

    public void setSessionId26(Integer sessionId26){
        this.sessionId26 = sessionId26;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId11(){
        return programId11;
    }

    public void setProgramId11(Integer programId11){
        this.programId11 = programId11;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId12(){
        return programId12;
    }

    public void setProgramId12(Integer programId12){
        this.programId12 = programId12;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId13(){
        return programId13;
    }

    public void setProgramId13(Integer programId13){
        this.programId13 = programId13;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId27(){
        return sessionId27;
    }

    public void setSessionId27(Integer sessionId27){
        this.sessionId27 = sessionId27;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId14(){
        return programId14;
    }

    public void setProgramId14(Integer programId14){
        this.programId14 = programId14;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId28(){
        return sessionId28;
    }

    public void setSessionId28(Integer sessionId28){
        this.sessionId28 = sessionId28;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId15(){
        return programId15;
    }

    public void setProgramId15(Integer programId15){
        this.programId15 = programId15;
    }

    /**
     * 
     * 
     */
    public Integer getNameStringId4(){
        return nameStringId4;
    }

    public void setNameStringId4(Integer nameStringId4){
        this.nameStringId4 = nameStringId4;
    }

    /**
     * 
     * 
     */
    public Integer getValueStringId(){
        return valueStringId;
    }

    public void setValueStringId(Integer valueStringId){
        this.valueStringId = valueStringId;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId16(){
        return programId16;
    }

    public void setProgramId16(Integer programId16){
        this.programId16 = programId16;
    }

    /**
     * 
     * 
     */
    public Integer getNameStringId5(){
        return nameStringId5;
    }

    public void setNameStringId5(Integer nameStringId5){
        this.nameStringId5 = nameStringId5;
    }

    /**
     * 
     * 
     */
    public Integer getSessionId29(){
        return sessionId29;
    }

    public void setSessionId29(Integer sessionId29){
        this.sessionId29 = sessionId29;
    }

    /**
     * 
     * 
     */
    public Integer getProgramId17(){
        return programId17;
    }

    public void setProgramId17(Integer programId17){
        this.programId17 = programId17;
    }

    /**
     * 
     * 
     */
    public Integer getNameStringId6(){
        return nameStringId6;
    }

    public void setNameStringId6(Integer nameStringId6){
        this.nameStringId6 = nameStringId6;
    }



}
