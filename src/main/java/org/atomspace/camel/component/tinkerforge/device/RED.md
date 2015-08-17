##RED


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             lifetime |       Long |
|            sessionId |    Integer |
|           sessionId2 |    Integer |
|           sessionId3 |    Integer |
|            lifetime2 |       Long |
|             objectId |    Integer |
|           sessionId4 |    Integer |
|            objectId2 |    Integer |
|           sessionId5 |    Integer |
|      lengthToReserve |       Long |
|               buffer |     String |
|           sessionId6 |    Integer |
|             stringId |    Integer |
|               length |       Long |
|            stringId2 |    Integer |
|            stringId3 |    Integer |
|               offset |       Long |
|              buffer2 |     String |
|            stringId4 |    Integer |
|              offset2 |       Long |
|     lengthToReserve2 |    Integer |
|           sessionId7 |    Integer |
|               listId |    Integer |
|              listId2 |    Integer |
|                index |    Integer |
|           sessionId8 |    Integer |
|              listId3 |    Integer |
|         itemObjectId |    Integer |
|              listId4 |    Integer |
|               index2 |    Integer |
|         nameStringId |    Integer |
|                flags |       Long |
|          permissions |    Integer |
|                 uid2 |       Long |
|                  gid |       Long |
|           sessionId9 |    Integer |
|               flags2 |       Long |
|              length2 |       Long |
|          sessionId10 |    Integer |
|               fileId |    Integer |
|          sessionId11 |    Integer |
|              fileId2 |    Integer |
|         lengthToRead |      Short |
|              fileId3 |    Integer |
|        lengthToRead2 |       Long |
|              fileId4 |    Integer |
|              fileId5 |    Integer |
|              buffer3 |    short[] |
|        lengthToWrite |      Short |
|              fileId6 |    Integer |
|              buffer4 |    short[] |
|       lengthToWrite2 |      Short |
|              fileId7 |    Integer |
|              buffer5 |    short[] |
|       lengthToWrite3 |      Short |
|              fileId8 |    Integer |
|              offset3 |       Long |
|               origin |      Short |
|              fileId9 |    Integer |
|             fileId10 |    Integer |
|               events |    Integer |
|             fileId11 |    Integer |
|        nameStringId2 |    Integer |
|          sessionId12 |    Integer |
|          directoryId |    Integer |
|          sessionId13 |    Integer |
|         directoryId2 |    Integer |
|          sessionId14 |    Integer |
|         directoryId3 |    Integer |
|        nameStringId3 |    Integer |
|               flags3 |       Long |
|         permissions2 |    Integer |
|                 uid3 |       Long |
|                 gid2 |       Long |
|          sessionId15 |    Integer |
|   executableStringId |    Integer |
|      argumentsListId |    Integer |
|    environmentListId |    Integer |
| workingDirectoryStringId |    Integer |
|                 uid4 |       Long |
|                 gid3 |       Long |
|          stdinFileId |    Integer |
|         stdoutFileId |    Integer |
|         stderrFileId |    Integer |
|          sessionId16 |    Integer |
|            processId |    Integer |
|               signal |      Short |
|           processId2 |    Integer |
|          sessionId17 |    Integer |
|           processId3 |    Integer |
|           processId4 |    Integer |
|          sessionId18 |    Integer |
|           processId5 |    Integer |
|          sessionId19 |    Integer |
|   identifierStringId |    Integer |
|          sessionId20 |    Integer |
|            programId |    Integer |
|               cookie |       Long |
|           programId2 |    Integer |
|          sessionId21 |    Integer |
|           programId3 |    Integer |
|          sessionId22 |    Integer |
|           programId4 |    Integer |
|  executableStringId2 |    Integer |
|     argumentsListId2 |    Integer |
|   environmentListId2 |    Integer |
| workingDirectoryStringId2 |    Integer |
|           programId5 |    Integer |
|          sessionId23 |    Integer |
|           programId6 |    Integer |
|     stdinRedirection |      Short |
| stdinFileNameStringId |    Integer |
|    stdoutRedirection |      Short |
| stdoutFileNameStringId |    Integer |
|    stderrRedirection |      Short |
| stderrFileNameStringId |    Integer |
|           programId7 |    Integer |
|          sessionId24 |    Integer |
|           programId8 |    Integer |
|            startMode |      Short |
|   continueAfterError |    Boolean |
|        startInterval |       Long |
|  startFieldsStringId |    Integer |
|           programId9 |    Integer |
|          sessionId25 |    Integer |
|          programId10 |    Integer |
|          sessionId26 |    Integer |
|          programId11 |    Integer |
|          programId12 |    Integer |
|          programId13 |    Integer |
|          sessionId27 |    Integer |
|          programId14 |    Integer |
|          sessionId28 |    Integer |
|          programId15 |    Integer |
|        nameStringId4 |    Integer |
|        valueStringId |    Integer |
|          programId16 |    Integer |
|        nameStringId5 |    Integer |
|          sessionId29 |    Integer |
|          programId17 |    Integer |
|        nameStringId6 |    Integer |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|        createSession |                                 lifetime |
|        expireSession |                                sessionId |
| expireSessionUnchecked |                               sessionId2 |
|     keepSessionAlive |                    sessionId3, lifetime2 |
|        releaseObject |                     objectId, sessionId4 |
| releaseObjectUnchecked |                    objectId2, sessionId5 |
|       allocateString |      lengthToReserve, buffer, sessionId6 |
|       truncateString |                         stringId, length |
|      getStringLength |                                stringId2 |
|       setStringChunk |               stringId3, offset, buffer2 |
|       getStringChunk |                       stringId4, offset2 |
|         allocateList |             lengthToReserve2, sessionId7 |
|        getListLength |                                   listId |
|          getListItem |               listId2, index, sessionId8 |
|         appendToList |                    listId3, itemObjectId |
|       removeFromList |                          listId4, index2 |
|             openFile | nameStringId, flags, permissions, uid2, gid, sessionId9 |
|           createPipe |             flags2, length2, sessionId10 |
|          getFileInfo |                      fileId, sessionId11 |
|             readFile |                    fileId2, lengthToRead |
|        readFileAsync |                   fileId3, lengthToRead2 |
|   abortAsyncFileRead |                                  fileId4 |
|            writeFile |          fileId5, buffer3, lengthToWrite |
|   writeFileUnchecked |         fileId6, buffer4, lengthToWrite2 |
|       writeFileAsync |         fileId7, buffer5, lengthToWrite3 |
|      setFilePosition |                 fileId8, offset3, origin |
|      getFilePosition |                                  fileId9 |
|        setFileEvents |                         fileId10, events |
|        getFileEvents |                                 fileId11 |
|        openDirectory |               nameStringId2, sessionId12 |
|     getDirectoryName |                 directoryId, sessionId13 |
| getNextDirectoryEntry |                directoryId2, sessionId14 |
|      rewindDirectory |                             directoryId3 |
|      createDirectory | nameStringId3, flags3, permissions2, uid3, gid2 |
|         getProcesses |                              sessionId15 |
|         spawnProcess | executableStringId, argumentsListId, environmentListId, workingDirectoryStringId, uid4, gid3, stdinFileId, stdoutFileId, stderrFileId, sessionId16 |
|          killProcess |                        processId, signal |
|    getProcessCommand |                  processId2, sessionId17 |
|   getProcessIdentity |                               processId3 |
|      getProcessStdio |                  processId4, sessionId18 |
|      getProcessState |                               processId5 |
|          getPrograms |                              sessionId19 |
|        defineProgram |          identifierStringId, sessionId20 |
|         purgeProgram |                        programId, cookie |
| getProgramIdentifier |                  programId2, sessionId21 |
| getProgramRootDirectory |                  programId3, sessionId22 |
|    setProgramCommand | programId4, executableStringId2, argumentsListId2, environmentListId2, workingDirectoryStringId2 |
|    getProgramCommand |                  programId5, sessionId23 |
| setProgramStdioRedirection | programId6, stdinRedirection, stdinFileNameStringId, stdoutRedirection, stdoutFileNameStringId, stderrRedirection, stderrFileNameStringId |
| getProgramStdioRedirection |                  programId7, sessionId24 |
|   setProgramSchedule | programId8, startMode, continueAfterError, startInterval, startFieldsStringId |
|   getProgramSchedule |                  programId9, sessionId25 |
| getProgramSchedulerState |                 programId10, sessionId26 |
| continueProgramSchedule |                              programId11 |
|         startProgram |                              programId12 |
| getLastSpawnedProgramProcess |                 programId13, sessionId27 |
| getCustomProgramOptionNames |                 programId14, sessionId28 |
| setCustomProgramOptionValue | programId15, nameStringId4, valueStringId |
| getCustomProgramOptionValue |  programId16, nameStringId5, sessionId29 |
| removeCustomProgramOption |               programId17, nameStringId6 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|        asyncFileRead | firedBy, fileId, errorCode, buffer, lengthRead |
|       asyncFileWrite | firedBy, fileId, errorCode, lengthWritten |
|   fileEventsOccurred |                  firedBy, fileId, events |
|  processStateChanged | firedBy, processId, state, timestamp, exitCode |
| programSchedulerStateChanged |                       firedBy, programId |
| programProcessSpawned |                       firedBy, programId |


