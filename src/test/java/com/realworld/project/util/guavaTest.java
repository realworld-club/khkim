package com.realworld.project.util;

import org.junit.jupiter.api.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class guavaTest {

    @Test
    void checkargumentTest() {
        checkArgument(1 > 0);
    }

    @Test
    void checkargumentExceptionTest() {
        assertThatThrownBy(() -> checkArgument(1 < 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkNotNullExceptionTest() {
        String str = null;
        assertThatThrownBy(() -> checkNotNull(str))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void checkNotNullTest() {
        String str = "STR";
        checkNotNull(str);
    }

    @Test
    void exceptionMessageTest() {
        String str = null;
        assertThatThrownBy(() -> checkNotNull(str, "test message"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContainingAll("test message");
    }
}
