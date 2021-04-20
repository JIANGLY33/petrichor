package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.ObjectType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemoryInfoVisitor {

    @Autowired
    ContextUtil contextUtil;

    public Long getStringMem() {
        return contextUtil.getObjectMem(ObjectType.PETRICHOR_STRING);
    }

    public Long getListMem() {
        return contextUtil.getObjectMem(ObjectType.PETRICHOR_LIST);
    }

    public Long getSetMem() {
        return contextUtil.getObjectMem(ObjectType.PETRICHOR_SET);
    }

    public Long getMapMem() {
        return contextUtil.getObjectMem(ObjectType.PETRICHOR_MAP);
    }

    public Long getSumMem() {
        return getListMem()+getStringMem()+getSetMem()+getMapMem();
    }
}
