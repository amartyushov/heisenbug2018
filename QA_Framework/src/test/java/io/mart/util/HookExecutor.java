package io.mart.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HookExecutor {

    private String currentHookFlag;

    public void updateHookFlagValue(String newFlagValue) {
        currentHookFlag = newFlagValue;
    }

    private boolean isSameHook(String hookFlag) {
        return hookFlag.equalsIgnoreCase(currentHookFlag);
    }

    public void runBasedOnFlag(String hookFlag, Runnable function) {
        if (!isSameHook(hookFlag)) {
            updateHookFlagValue(hookFlag);
            log.debug("Executing hook @{}", hookFlag);
            function.run();
        } else {
            log.debug("Skipping hook @{} execution because it was executed before", hookFlag);
        }
    }
}
