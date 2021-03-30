package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotSpotInfoVisitor {

    @Autowired
    ContextUtil contextUtil;
}
