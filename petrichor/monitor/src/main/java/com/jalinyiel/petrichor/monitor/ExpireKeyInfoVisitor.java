package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpireKeyInfoVisitor {

    @Autowired
    ContextUtil contextUtil;
}
