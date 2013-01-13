package com.passbook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

import java.util.Set;

public class Log {

    @JsonProperty
    private Set<String> logs = Sets.newHashSet();

    public Log(Set<String> logs) {
        this.logs = logs;
    }

    public Set<String> getLogs() {
        return logs;
    }

    public void setLogs(Set<String> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("logs", logs)
                .toString();
    }
}
