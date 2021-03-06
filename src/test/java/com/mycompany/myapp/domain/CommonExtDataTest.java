package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonExtDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonExtData.class);
        CommonExtData commonExtData1 = new CommonExtData();
        commonExtData1.setId(1L);
        CommonExtData commonExtData2 = new CommonExtData();
        commonExtData2.setId(commonExtData1.getId());
        assertThat(commonExtData1).isEqualTo(commonExtData2);
        commonExtData2.setId(2L);
        assertThat(commonExtData1).isNotEqualTo(commonExtData2);
        commonExtData1.setId(null);
        assertThat(commonExtData1).isNotEqualTo(commonExtData2);
    }
}
