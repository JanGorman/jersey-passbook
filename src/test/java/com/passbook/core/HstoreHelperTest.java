package com.passbook.core;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class HstoreHelperTest {

    @Test
    public void testEmptyMapToString() {
        Map<String, String> m = Maps.newHashMap();
        assertThat(HstoreHelper.toString(m)).isEqualTo("");
    }

    @Test
    public void testToString() {
        Map<String, String> m = Maps.newHashMap();
        m.put("foo", "bar");
        assertThat(HstoreHelper.toString(m)).isEqualTo("foo=>bar");
    }

    @Test
    public void testToString2() {
        Map<String, String> m = Maps.newLinkedHashMap();
        m.put("foo", "bar");
        m.put("xxx", "yyy");
        assertThat(HstoreHelper.toString(m)).isEqualTo("foo=>bar, xxx=>yyy");
    }

    @Test
    public void testEmptyStringToMap() {
        Map<String, String> m = HstoreHelper.toMap("");
        assertThat(m).isEmpty();
    }

    @Test
    public void testToMap() {
        Map<String, String> m = HstoreHelper.toMap("\"foo\"=>\"bar\"");
        assertThat(m).hasSize(1);
        assertThat(m.get("foo")).isEqualTo("bar");
    }

    @Test
    public void testToMap2() {
        Map<String, String> m = HstoreHelper.toMap("\"foo\"=>\"bar\", \"xxx\"=>\"yyy\"");
        assertThat(m).hasSize(2);
        assertThat(m.get("foo")).isEqualTo("bar");
        assertThat(m.get("xxx")).isEqualTo("yyy");
    }

}
