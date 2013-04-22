package com.passbook.core;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Map;

class HstoreHelper {

    private static final String SEPARATOR = "=>";

    private static final Predicate<String> IS_BLANK = matchesAllOf(CharMatcher.WHITESPACE);

    private static final Predicate<String> IS_NULL_OR_BLANK = Predicates.and(Predicates.notNull(), IS_BLANK);

    private static Predicate<String> matchesAllOf(final CharMatcher charMatcher) {
        return new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String s) {
                return charMatcher.matchesAllOf(s);
            }
        };
    }

    public static String toString(Map<String, String> map) {
        if (map.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int size = map.size();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append(entry.getKey());
            builder.append(SEPARATOR);
            builder.append(entry.getValue());
            if (size > 1) {
                builder.append(", ");
                size--;
            }
        }

        return builder.toString();
    }

    public static Map<String, String> toMap(String s) {
        Map<String, String> map = Maps.newHashMap();
        if (s == null || IS_NULL_OR_BLANK.apply(s)) {
            return map;
        }

        String[] tokens = s.split(", ");
        for (String token : tokens) {
            String[] keyValue = token.split(SEPARATOR);
            String key = keyValue[0];
            key = key.trim().substring(1, key.length() - 1);
            String value = keyValue[1];
            value = value.trim().substring(1, value.length() - 1);
            map.put(key, value);
        }

        return map;
    }

}
