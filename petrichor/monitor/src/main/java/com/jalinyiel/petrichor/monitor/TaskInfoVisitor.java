package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.ContextUtil;
import com.jalinyiel.petrichor.core.PetrichorUtil;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TaskInfoVisitor {

    @Autowired
    ContextUtil contextUtil;

    public final int TASK_INFO_CAPACITY = 10;

    Map<TaskType, TreeMap<String, Long>> data;

    @PostConstruct
    public void getTaskStatistics() {
        this.data = contextUtil.getDataTypeTaskCount();
    }

    public List<String> getTimes() {
        Set<String> times = new HashSet<>();
        data.entrySet().stream().forEach(entry -> {
            entry.getValue().entrySet().stream().forEach(valueEntry -> times.add(valueEntry.getKey()));
        });
        List<String> sortedTimes = times.stream().sorted(PetrichorUtil::timeCompare).collect(Collectors.toList());
        return paddingTimes(sortedTimes);
    }

    public List<Long> getTaskCount(TaskType taskType) {
        List<String> timeLine = this.getTimes();
        List<Long> taskCount = new LinkedList<>();
        Map<String, Long> count = data.get(taskType);
        timeLine.stream().forEach(time -> {
            if (count.containsKey(time)) taskCount.add(count.get(time));
            else taskCount.add(0L);
        });
        return taskCount;
    }

    private List<String> paddingTimes(List<String> times) {
        int padSize = TASK_INFO_CAPACITY == times.size() ? 0 : TASK_INFO_CAPACITY - times.size();
        Optional<String> earliestTime = times.stream().findAny();

        List<String> paddingTimes = IntStream.range(0, padSize).boxed().map(integer -> {
            LocalTime baseTime = earliestTime.isPresent() ? LocalTime.parse(earliestTime.get()) : LocalTime.now();
            LocalTime shiftTime = baseTime.minusMinutes(padSize - integer);
            return shiftTime.format(DateTimeFormatter.ofPattern("hh:mm"));
        }).collect(Collectors.toList());
        if (times.size() > 0) paddingTimes.addAll(times);
        return paddingTimes;
    }

}

