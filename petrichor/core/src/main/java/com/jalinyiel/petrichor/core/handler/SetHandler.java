package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.collect.PetrichorSet;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.SetOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.core.util.PetrichorUtil;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
            Optional<PetrichorSet> optionalSet = this.getValue(key);
            if (!optionalSet.isPresent()) {
                return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't set!");
            }
            optionalSet.get().add(elements);
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<Void> setRemove(String key, String... elements) {
        Optional<PetrichorSet> optionalSet = this.getValue(key);
        if (!optionalSet.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't set!");
        }
        optionalSet.get().remove(elements);
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<Integer> setSize(String key) {
        Optional<PetrichorSet> optionalSet = this.getValue(key);
        if (!optionalSet.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't set!");
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS, optionalSet.get().size());
    }

    @Override
    @CheckKey
    public ResponseResult<String> setGet(String key) {
        Optional<PetrichorSet> optionalSet = this.getValue(key);
        if (!optionalSet.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't set!");
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS, optionalSet.get().toString());
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
                .map(key -> this.getValue(key).get()).collect(Collectors.toList());
        PetrichorSet petrichorSet = petrichorSets.stream().reduce(accumulator).get();
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorSet.toString());
    }

    private Optional<PetrichorSet> getValue(String key) {
        try {
            PetrichorSet petrichorSet = contextUtil.getValue(key);
            return Optional.of(petrichorSet);
        } catch (ClassCastException classCastException) {
            return Optional.empty();
        }
    }
}
