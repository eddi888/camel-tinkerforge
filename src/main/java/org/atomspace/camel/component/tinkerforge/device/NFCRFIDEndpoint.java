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
import org.apache.camel.spi.UriEndpoint;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletNFCRFID;

/**
 * Reads and writes NFC and RFID tags
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]nfcrfid", consumerClass = NFCRFIDConsumer.class, label = "iot", title = "Tinkerforge")
public class NFCRFIDEndpoint extends TinkerforgeEndpoint<NFCRFIDConsumer, NFCRFIDProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(NFCRFIDEndpoint.class);

    public static final String TAGTYPE="tagType";
    public static final String PAGE="page";
    public static final String KEYNUMBER="keyNumber";
    public static final String KEY="key";
    public static final String PAGE2="page2";
    public static final String DATA="data";
    public static final String PAGE3="page3";

    
    private Short tagType;
    private Integer page;
    private Short keyNumber;
    private short[] key;
    private Integer page2;
    private short[] data;
    private Integer page3;

        
    public NFCRFIDEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new NFCRFIDProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new NFCRFIDConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletNFCRFID device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletNFCRFID device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "requestTagId":
                device.requestTagID(
                        getValue(short.class, "tagType", m, getTagType())
                    );
                break;

            case "getTagId":
                response = device.getTagID();
                break;

            case "getState":
                response = device.getState();
                break;

            case "authenticateMifareClassicPage":
                device.authenticateMifareClassicPage(
                        getValue(int.class, "page", m, getPage()),
                        getValue(short.class, "keyNumber", m, getKeyNumber()),
                        getValue(short[].class, "key", m, getKey())
                    );
                break;

            case "writePage":
                device.writePage(
                        getValue(int.class, "page2", m, getPage2()),
                        getValue(short[].class, "data", m, getData())
                    );
                break;

            case "requestPage":
                device.requestPage(
                        getValue(int.class, "page3", m, getPage3())
                    );
                break;

            case "getPage":
                response = device.getPage();
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
     * To read or write a tag that is in proximity of the NFC/RFID Bricklet you 
     * first have to call this function with the expected tag type as parameter.
     * It is no problem if you don't know the tag type. You can cycle through 
     * the available tag types until the tag gives an answer to the request.
     * 
     * Current the following tag types are supported:
     * 
     * * Mifare Classic (``tag_type`` = 0)
     * * NFC Forum Type 1 (``tag_type`` = 1)
     * * NFC Forum Type 2 (``tag_type`` = 2)
     * 
     * After you call :func:`RequestTagID` the NFC/RFID Bricklet will try to read 
     * the tag ID from the tag. After this process is done the state will change.
     * You can either register the :func:`StateChanged` callback or you can poll
     * :func:`GetState` to find out about the state change.
     * 
     * If the state changes to *RequestTagIDError* it means that either there was 
     * no tag present or that the tag is of an incompatible type. If the state 
     * changes to *RequestTagIDReady* it means that a compatible tag was found 
     * and that the tag ID could be read out. You can now get the tag ID by
     * calling :func:`GetTagID`.
     * 
     * If two tags are in the proximity of the NFC/RFID Bricklet, this
     * function will cycle through the tags. To select a specific tag you have
     * to call :func:`RequestTagID` until the correct tag id is found.
     * 
     * In case of any *Error* state the selection is lost and you have to
     * start again by calling :func:`RequestTagID`.
     * 
     */
    public Short getTagType(){
        return tagType;
    }

    public void setTagType(Short tagType){
        this.tagType = tagType;
    }

    /**
     * 
     * Mifare Classic tags use authentication. If you want to read from or write to
     * a Mifare Classic page you have to authenticate it beforehand.
     * Each page can be authenticated with two keys: A (``key_number`` = 0) and B
     * (``key_number`` = 1). A new Mifare Classic
     * tag that has not yet been written to can can be accessed with key A
     * and the default key ``[0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF]``.
     * 
     * The approach to read or write a Mifare Classic page is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState`
     *    or :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`AuthenticateMifareClassicPage` with page and key for the page
     * 5. Wait for state to change to *AuthenticatingMifareClassicPageReady* (see
     *    :func:`GetState` or :func:`StateChanged`)
     * 6. Call :func:`RequestPage` or :func:`WritePage` to read/write page
     * 
     */
    public Integer getPage(){
        return page;
    }

    public void setPage(Integer page){
        this.page = page;
    }

    /**
     * 
     * Mifare Classic tags use authentication. If you want to read from or write to
     * a Mifare Classic page you have to authenticate it beforehand.
     * Each page can be authenticated with two keys: A (``key_number`` = 0) and B
     * (``key_number`` = 1). A new Mifare Classic
     * tag that has not yet been written to can can be accessed with key A
     * and the default key ``[0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF]``.
     * 
     * The approach to read or write a Mifare Classic page is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState`
     *    or :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`AuthenticateMifareClassicPage` with page and key for the page
     * 5. Wait for state to change to *AuthenticatingMifareClassicPageReady* (see
     *    :func:`GetState` or :func:`StateChanged`)
     * 6. Call :func:`RequestPage` or :func:`WritePage` to read/write page
     * 
     */
    public Short getKeyNumber(){
        return keyNumber;
    }

    public void setKeyNumber(Short keyNumber){
        this.keyNumber = keyNumber;
    }

    /**
     * 
     * Mifare Classic tags use authentication. If you want to read from or write to
     * a Mifare Classic page you have to authenticate it beforehand.
     * Each page can be authenticated with two keys: A (``key_number`` = 0) and B
     * (``key_number`` = 1). A new Mifare Classic
     * tag that has not yet been written to can can be accessed with key A
     * and the default key ``[0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF]``.
     * 
     * The approach to read or write a Mifare Classic page is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState`
     *    or :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`AuthenticateMifareClassicPage` with page and key for the page
     * 5. Wait for state to change to *AuthenticatingMifareClassicPageReady* (see
     *    :func:`GetState` or :func:`StateChanged`)
     * 6. Call :func:`RequestPage` or :func:`WritePage` to read/write page
     * 
     */
    public short[] getKey(){
        return key;
    }

    public void setKey(short[] key){
        this.key = key;
    }

    /**
     * 
     * Writes 16 bytes starting from the given page. How many pages are written
     * depends on the tag type. The page sizes are as follows:
     * 
     * * Mifare Classic page size: 16 byte (one page is written)
     * * NFC Forum Type 1 page size: 8 byte (two pages are written)
     * * NFC Forum Type 2 page size: 4 byte (four pages are written)
     * 
     * The general approach for writing to a tag is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState` or
     *    :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`WritePage` with page number and data
     * 5. Wait for state to change to *WritePageReady* (see :func:`GetState` or
     *    :func:`StateChanged`)
     * 
     * If you use a Mifare Classic tag you have to authenticate a page before you
     * can write to it. See :func:`AuthenticateMifareClassicPage`.
     * 
     */
    public Integer getPage2(){
        return page2;
    }

    public void setPage2(Integer page2){
        this.page2 = page2;
    }

    /**
     * 
     * Writes 16 bytes starting from the given page. How many pages are written
     * depends on the tag type. The page sizes are as follows:
     * 
     * * Mifare Classic page size: 16 byte (one page is written)
     * * NFC Forum Type 1 page size: 8 byte (two pages are written)
     * * NFC Forum Type 2 page size: 4 byte (four pages are written)
     * 
     * The general approach for writing to a tag is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState` or
     *    :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`WritePage` with page number and data
     * 5. Wait for state to change to *WritePageReady* (see :func:`GetState` or
     *    :func:`StateChanged`)
     * 
     * If you use a Mifare Classic tag you have to authenticate a page before you
     * can write to it. See :func:`AuthenticateMifareClassicPage`.
     * 
     */
    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    /**
     * 
     * Reads 16 bytes starting from the given page and stores them into a buffer. 
     * The buffer can then be read out with :func:`GetPage`.
     * How many pages are read depends on the tag type. The page sizes are 
     * as follows:
     * 
     * * Mifare Classic page size: 16 byte (one page is read)
     * * NFC Forum Type 1 page size: 8 byte (two pages are read)
     * * NFC Forum Type 2 page size: 4 byte (four pages are read)
     * 
     * The general approach for reading a tag is as follows:
     * 
     * 1. Call :func:`RequestTagID`
     * 2. Wait for state to change to *RequestTagIDReady* (see :func:`GetState`
     *    or :func:`StateChanged`)
     * 3. If looking for a specific tag then call :func:`GetTagID` and check if the
     *    expected tag was found, if it was not found got back to step 1
     * 4. Call :func:`RequestPage` with page number
     * 5. Wait for state to change to *RequestPageReady* (see :func:`GetState`
     *    or :func:`StateChanged`)
     * 6. Call :func:`GetPage` to retrieve the page from the buffer
     * 
     * If you use a Mifare Classic tag you have to authenticate a page before you
     * can read it. See :func:`AuthenticateMifareClassicPage`.
     * 
     */
    public Integer getPage3(){
        return page3;
    }

    public void setPage3(Integer page3){
        this.page3 = page3;
    }



}
