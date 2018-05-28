package algorithms.tasks.test;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MyMapDecoderTest {

    private MyMapDecoder mapDecoder;

    @Before
    public void testSetup() throws Exception {
        mapDecoder = new MyMapDecoder();
    }

    @Test
    public void decodeMap() throws Exception {
        //given
        final String input = "one=1&two=2";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("two", "2");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWeirdButGood() throws Exception {
        //given
        final String input = "one=1&";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWithDupes() throws Exception {
        //given
        final String input = "one=1&two=2&one=-1";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("one", "-1");
        expected.put("two", "2");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWithEmptyValue() throws Exception {
        //given
        final String input = "one=&two=2";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("one", "");
        expected.put("two", "2");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWithEmptyValue2() throws Exception {
        //given
        final String input = "one=1&two=";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("two", "");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWithEmptyName() throws Exception {
        //given
        final String input = "=1&two=2";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        expected.put("", "1");
        expected.put("two", "2");

        assertEquals(expected, result);
    }

    @Test
    public void decodeMapWithNullInput() throws Exception {
        //given
        final String input = null;

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        assertNull(result);
    }

    @Test
    public void decodeMapWithEmptyInput() throws Exception {
        //given
        final String input = "";

        //when
        Map<String, String> result = mapDecoder.decode(input);

        //then
        Map<String, String> expected = new HashMap<>();
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeMapWithInvalidInput() throws Exception {
        //given
        final String input = "=";

        //when
        mapDecoder.decode(input);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeMapWithInvalidInput2() throws Exception {
        //given
        final String input = "&one=1";

        //when
        mapDecoder.decode(input);

        //then
    }


}