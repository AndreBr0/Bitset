import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Test {
    Bitset<String> bitset1;
    Bitset<String> bitset2;
    @BeforeEach
    public void before(){
        bitset1 = new Bitset<String>(5);
        bitset2 = new Bitset<String>(5);

    }

    @org.junit.jupiter.api.Test
    public void add(){
         bitset1.add("s");
         bitset1.add("99");
         bitset1.add("ooo");
         bitset1.add("4");
         bitset1.add("last");
         assertEquals(5, bitset1.size());
         assertEquals("s", bitset1.get(0));
         assertTrue(bitset1.contains("last"));
         assertArrayEquals(new Object[]{"s", "99", "ooo", "4", "last"} ,bitset1.getElements());
        assertThrows(IllegalArgumentException.class, () -> {
            bitset1.add("looser");
        });
        bitset1 = new Bitset<>(10);
        bitset1.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        assertArrayEquals(new Object[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                bitset1.getElements());
        bitset1.remove(new String[]{"1", "3", "5", "7", "9"});
        assertArrayEquals(new Object[]{"0", null, "2", null, "4", null, "6", null, "8", null}, bitset1.getElements());
        assertEquals(5, bitset1.elementsSize());
    }

    @org.junit.jupiter.api.Test
    public void remove(){
        bitset1.add("9");
        bitset1.add("o");
        bitset1.add("i");
        bitset1.add("55");
        bitset1.remove("o");
        bitset1.remove(3);
        assertFalse(bitset1.contains("o"));
        assertArrayEquals(new Object[]{"9", null, "i", null, null}, bitset1.getElements());
    }

    @org.junit.jupiter.api.Test
    public void union(){
        bitset1.add("9");
        bitset1.add("o");
        bitset1.add("i");

        bitset2.add("555");
        bitset2.add("o");
        bitset2.add("hhh");
        assertArrayEquals(new Object[]{"9", "o", "i", "555", "hhh", null, null, null, null, null},
                bitset1.union(bitset2).getElements());
    }

    @org.junit.jupiter.api.Test
    public void intersect(){
        bitset1.add("9");
        bitset1.add("o");
        bitset1.add("i");

        bitset2.add("666");
        bitset2.add("o");
        bitset2.add("9");
        assertArrayEquals(new Object[]{"9", "o", null, null, null, null, null, null, null, null},
                bitset1.intersect(bitset2).getElements());
    }

    @org.junit.jupiter.api.Test
    public void addition(){
        bitset1.add("99");
        bitset1.add("88");
        bitset1.add("77");
        bitset1.add("33");
        bitset1.add("22");

        bitset2.add("88");
        bitset2.add("99");
        bitset2.add("00");

        assertArrayEquals(new Object[]{null, null, "77", "33", "22"},
                bitset1.addition(bitset2).getElements());

    }

}
