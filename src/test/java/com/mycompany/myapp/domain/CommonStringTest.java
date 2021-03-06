package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonStringTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonString.class);
        CommonString commonString1 = new CommonString();
        commonString1.setId(1L);
        CommonString commonString2 = new CommonString();
        commonString2.setId(commonString1.getId());
        assertThat(commonString1).isEqualTo(commonString2);
        commonString2.setId(2L);
        assertThat(commonString1).isNotEqualTo(commonString2);
        commonString1.setId(null);
        assertThat(commonString1).isNotEqualTo(commonString2);
    }
}
