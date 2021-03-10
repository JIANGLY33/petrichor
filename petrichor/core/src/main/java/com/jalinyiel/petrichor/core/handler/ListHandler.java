package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.collect.PetrichorList;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.ListOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@DataType(type = TaskType.LIST_TASK)
public class ListHandler extends PetrichorHandler implements ListOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil contextUtil;

    private final ObjectType VALUE_TYPE = ObjectType.PETRICHOR_LIST;

    private final ObjectEncoding VALUE_ENCODING = ObjectEncoding.LINKED_LIST;

    @Override
    public ResponseResult<Void> rightPush(String key, String... values) {
        if (!contextUtil.keyExist(key)) {
            PetrichorList value = new PetrichorList();
            value.rightPush(values);
            PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
            PetrichorDict dict = petrichorDb.getKeyValues();
            dict.put(PetrichorObjectFactory.of(PetrichorUtil.KEY_TYPE, PetrichorUtil.KEY_ENCODING, new PetrichorString(key)),
                    PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, value));
        } else {
            PetrichorList petrichorList = contextUtil.getValue(key);
            petrichorList.rightPush(values);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<Void> leftPush(String key, String... values) {
        return null;
    }

    @Override
    public ResponseResult<Void> listInsert(String key, boolean isBefore, int pivot, String value) {
        return null;
    }

    @Override
    public ResponseResult<String> listIndex(String key, int index) {
        return null;
    }

    @Override
    public ResponseResult<List<String>> listRange(String key, int start, int end) {
        return null;
    }

    @Override
    @CheckKey
    public ResponseResult<Integer> listLength(String key) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.size());
    }

    @Override
    public ResponseResult<String> leftPop(String key) {
        return null;
    }

    @Override
    public ResponseResult<String> rightPop(String key) {
        return null;
    }

    @Override
    public ResponseResult<String> listSetByIndex(String key, int index, String value) {
        return null;
    }

    @Override
    public ResponseResult<Integer> listTrim(String key, int start, int end) {
        return null;
    }

}
