package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorSet;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.SetOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Component
@DataType(type = TaskType.SET_TASK)
public class SetHandler extends PetrichorHandler implements SetOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil<PetrichorSet> contextUtil;

    private final ObjectType VALUE_TYPE = ObjectType.PETRICHOR_SET;

    private final ObjectEncoding VALUE_ENCODING = ObjectEncoding.HASH_SET;

    @Override
    public ResponseResult<Void> setAdd(String key, String... elements) {
        if (!contextUtil.keyExist(key)) {
            PetrichorSet petrichorSet = new PetrichorSet(Arrays.stream(elements).collect(Collectors.toSet()));
            PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
            PetrichorDict dict = petrichorDb.getKeyValues();
            dict.put(PetrichorObjectFactory.of(PetrichorUtil.KEY_TYPE, PetrichorUtil.KEY_ENCODING, new PetrichorString(key)),
                    PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, petrichorSet));
        } else {
            PetrichorSet petrichorSet = contextUtil.getValue(key);
            petrichorSet.add(elements);
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<Void> setRemove(String key, String... elements) {
        PetrichorSet petrichorSet = contextUtil.getValue(key);
        petrichorSet.remove(elements);
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<Integer> setSize(String key) {
        PetrichorSet petrichorSet = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorSet.size());
    }

    @Override
    public ResponseResult<String> setGet(String key) {
        PetrichorSet petrichorSet = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorSet.toString());
    }

    @Override
    public ResponseResult<String> setIntersect(String... keys) {
        return foldSet(PetrichorSet::intersect, keys);
    }

    @Override
    public ResponseResult<String> setUnion(String... keys) {
        return foldSet(PetrichorSet::union, keys);
    }

    @Override
    public ResponseResult<String> setComplementary(String... keys) {
        return foldSet(PetrichorSet::complementary, keys);
    }

    private ResponseResult<String> foldSet(BinaryOperator<PetrichorSet> accumulator, String... keys) {
        List<PetrichorSet> petrichorSets = Arrays.stream(keys)
                .map(key -> (PetrichorSet) contextUtil.getValue(key)).collect(Collectors.toList());
        PetrichorSet petrichorSet = petrichorSets.stream().reduce(accumulator).get();
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorSet.toString());
    }
}
