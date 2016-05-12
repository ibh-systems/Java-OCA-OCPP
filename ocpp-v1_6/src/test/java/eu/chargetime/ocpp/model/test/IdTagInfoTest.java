package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.IdTagInfo;
import org.junit.*;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public class IdTagInfoTest {
    private IdTagInfo idTagInfo;

    @Before
    public void setUp() throws Exception {
        idTagInfo = new IdTagInfo();
    }

    @Test
    public void setStatus_illegalValue_throwsPropertyConstraintException() {
        // Given
        // Legal values: Accepted, Blocked, Expired, Invalid, ConcurrentTx
        String illegalValue = "something";

        // When
        try {
            idTagInfo.setStatus(illegalValue);
            Assert.fail("Expected exception");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("status"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setParentIdTag_stringLength21_throwsPropertyConstraintException() {
        // Given
        String illegalValue = "123456789012345678901";

        // When
        try {
            idTagInfo.setParentIdTag(illegalValue);
            Assert.fail("Excepted exception");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("parentIdTag"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void validate_statusNotSet_returnsFalse() {
        // When
        boolean isValid = idTagInfo.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_statusSet_returnsTrue() throws Exception {
        // Given
        idTagInfo.setStatus("Accepted");

        // When
        boolean isValid = idTagInfo.validate();

        // Then
        assertThat(isValid, is(true));
    }
}