/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webauthn4j.response.attestation.statement;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TPMIAlgHashTest {

    @Test
    public void create_test() throws InvalidFormatException {

        assertThat(TPMIAlgHash.create(0x0)).isEqualTo(TPMIAlgHash.TPM_ALG_ERROR);
        assertThat(TPMIAlgHash.create(0x04)).isEqualTo(TPMIAlgHash.TPM_ALG_SHA1);
        assertThat(TPMIAlgHash.create(0x0B)).isEqualTo(TPMIAlgHash.TPM_ALG_SHA256);
        assertThat(TPMIAlgHash.create(0x0C)).isEqualTo(TPMIAlgHash.TPM_ALG_SHA384);
        assertThat(TPMIAlgHash.create(0x0D)).isEqualTo(TPMIAlgHash.TPM_ALG_SHA512);
        assertThat(TPMIAlgHash.create(0x10)).isEqualTo(TPMIAlgHash.TPM_ALG_NULL);
    }

    @Test(expected = InvalidFormatException.class)
    public void create_with_invalid_value_test() throws InvalidFormatException {

        TPMIAlgPublic.create(0x2);
    }
}