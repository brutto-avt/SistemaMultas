package util;

import org.jdesktop.beansbinding.Converter;

public class DoubleConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return value;
    }

    @Override
    public Object convertReverse(Object value) {
        return new Double(value.toString());
    }
    
}
