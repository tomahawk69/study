package org.study.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Base64;

/**
 * Created by yur on 28.10.2015.
 */
public class HBaseSerializerUtils<T> {
    private final Class<T> classT;

    public HBaseSerializerUtils(Class<T> classT) {
        this.classT = classT;
    }

    public static String decodeString(String input) {
        String result = new String(Base64.getDecoder().decode(input));
        return result;
    }

    public T xmlToTable(String input) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classT);
            Unmarshaller u = jaxbContext.createUnmarshaller();
            return (T) u.unmarshal(new StringReader(input));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
