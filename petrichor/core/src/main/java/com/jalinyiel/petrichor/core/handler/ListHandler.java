package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.collect.PetrichorList;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.ListOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.core.util.PetrichorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@DataType(type = TaskType.LIST_TASK)
public class ListHandler extends PetrichorHandler implements ListOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil<PetrichorList> contextUtil;

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
        if (!contextUtil.keyExist(key)) {
            PetrichorList value = new PetrichorList();
            value.leftPush(values);
            PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
            PetrichorDict dict = petrichorDb.getKeyValues();
            dict.put(PetrichorObjectFactory.of(PetrichorUtil.KEY_TYPE, PetrichorUtil.KEY_ENCODING, new PetrichorString(key)),
                    PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, value));
        } else {
            PetrichorList petrichorList = contextUtil.getValue(key);
            petrichorList.leftPush(values);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<Void> listInsert(String key, boolean isBefore, int pivot, String value) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        if (isBefore) petrichorList.insert(value, pivot);
        else petrichorList.insert(value, pivot + 1);
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<String> listIndex(String key, int index) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.index(index));
    }

    @Override
    @CheckKey
    public ResponseResult<String> listRange(String key, int start, int end) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        String elements = petrichorList.range(start, end).stream().collect(Collectors.joining(","));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("[%s]",elements));
    }

    @Override
    @CheckKey
    public ResponseResult<Integer> listLength(String key) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.size());
    }

    @Override
    @CheckKey
    public ResponseResult<String> leftPop(String key) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.leftPop());
    }

    @Override
    @CheckKey
    public ResponseResult<String> rightPop(String key) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.rightPop());
    }

    @Override
    @CheckKey
    public ResponseResult<String> listSet(String key, int index, String value) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.set(value, index));
    }

    @Override
    @CheckKey
    public ResponseResult<String> listTrim(String key, int start, int end) {
        PetrichorList petrichorList = contextUtil.getValue(key);
        String elements = petrichorList.trim(start, end).stream().collect(Collectors.joining());
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("[%s]",elements));
    }

}
