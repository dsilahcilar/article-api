package com.example.algorithm;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringConverterTest {
    private StringConverter stringConverter = new StringConverter();

    @Test
    public void shouldConvertOneChange() {
        String source = "aex";
        String target = "axe";

        OperationList result = stringConverter.convert(source, target);

        assertThat(result.getOperations().get(1).getIndex(), is(2));
        assertThat(result.getOperations().get(1).getType(), is(OperationType.DELETE));
        assertThat(result.getOperations().get(1).getChr(), is('x'));
    }


    @Test
    public void shouldConvertSameChars() throws Exception {
        String source = "nrerefece";
        String target = "reference";

        OperationList result = stringConverter.convert(source, target);

        assertThat(result.getOperations().get(1).getIndex(), is(3));
        assertThat(result.getOperations().get(1).getType(), is(OperationType.DELETE));
        assertThat(result.getOperations().get(1).getChr(), is('r'));

        assertThat(result.getOperations().get(2).getIndex(), is(0));
        assertThat(result.getOperations().get(2).getType(), is(OperationType.PUT));
        assertThat(result.getOperations().get(2).getChr(), is('r'));

        assertThat(result.getOperations().get(4).getIndex(), is(1));
        assertThat(result.getOperations().get(4).getType(), is(OperationType.PUT));
        assertThat(result.getOperations().get(4).getChr(), is('e'));

        String source2 = "tationno";
        String target2 = "notation";

        OperationList result2 = stringConverter.convert(source2, target2);

        assertThat(result2.getOperations().get(1).getIndex(), is(6));
        assertThat(result2.getOperations().get(1).getType(), is(OperationType.DELETE));
        assertThat(result2.getOperations().get(1).getChr(), is('n'));

        assertThat(result2.getOperations().get(2).getIndex(), is(0));
        assertThat(result2.getOperations().get(2).getType(), is(OperationType.PUT));
        assertThat(result2.getOperations().get(2).getChr(), is('n'));

        assertThat(result2.getOperations().get(4).getIndex(), is(1));
        assertThat(result2.getOperations().get(4).getType(), is(OperationType.PUT));
        assertThat(result2.getOperations().get(4).getChr(), is('o'));

        String source3 = "guaeg";
        String target3 = "gauge";

        OperationList result3 = stringConverter.convert(source3, target3);

        assertThat(result3.getOperations().get(4).getIndex(), is(3));
        assertThat(result3.getOperations().get(4).getType(), is(OperationType.PUT));
        assertThat(result3.getOperations().get(4).getChr(), is('g'));
    }


    @Test
    public void shouldConvertWhenSourceBiggerThanTarget() throws Exception {
        String source = "yellowbigger";
        String target = "bigyellow";

        OperationList result = stringConverter.convert(source, target);

        assertThat(result.getOperations().get(1).getIndex(), is(6));
        assertThat(result.getOperations().get(1).getType(), is(OperationType.DELETE));
        assertThat(result.getOperations().get(1).getChr(), is('b'));

        assertThat(result.getOperations().get(8).getIndex(), is(9));
        assertThat(result.getOperations().get(8).getType(), is(OperationType.DELETE));
        assertThat(result.getOperations().get(8).getChr(), is('e'));

        assertThat(result.getOperations().get(9).getIndex(), is(9));
        assertThat(result.getOperations().get(9).getType(), is(OperationType.DELETE));
        assertThat(result.getOperations().get(9).getChr(), is('r'));
    }

}