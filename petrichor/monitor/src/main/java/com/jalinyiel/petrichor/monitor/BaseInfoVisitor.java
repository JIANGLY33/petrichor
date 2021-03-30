package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseInfoVisitor {

    @Autowired
    ContextUtil contextUtil;

    public long countKey() {
        return contextUtil.getKeySize();
    }

    public long countExpireKey(){
        return contextUtil.getExpireKeySize();
    }

    public long getRunTime() {
        return contextUtil.getRunDuration();
    }

    public long countHandledTask() {
        return contextUtil.getTaskNums();
    }
}
