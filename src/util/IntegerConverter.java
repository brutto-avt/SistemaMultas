package util;

import org.jdesktop.beansbinding.Converter;

public class IntegerConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return value;
    }

    @Override
    public Object convertReverse(Object value) {
        return new Integer(value.toString());
    }
    
}
