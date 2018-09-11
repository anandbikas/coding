package com.anand.coding.java.collection;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
public class SetDemo {

    private int m1;
    private int m2;

    SetDemo(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public String toString() {
        return "(" + m1 + "," + m2 + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SetDemo that = (SetDemo) o;
        return m1 == that.m1 &&
                m2 == that.m2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m1, m2);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        Set<SetDemo> set = new HashSet<>();

        set.add(new SetDemo(1,2));

        //SetDemo object with redundant m1,m2 won't be added again.
        set.add(new SetDemo(1,2));
        set.add(new SetDemo(1,3));

        System.out.println(set.toString());

    }
}
