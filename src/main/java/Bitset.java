import java.util.Arrays;

public class Bitset<T> {
    private int size;
    private Object[] elements;

    public Bitset(int size) {
        this.size = size;
        elements = new Object[size];
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    public Object[] getElements() {
        return elements;
    }

    public int size(){
        return size;
    }

    public int elementsSize(){
        int n = 0;
        for (Object o : elements){
            if (o != null){
                n++;
            }
        }
        return n;
    }

    public T get(int i){
        return (T) elements[i];
    }

    public void add(T o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    elements[i] = o;
                    break;
                }
                if (i == size-1) throw new IllegalArgumentException("Bitset is full");
            }

        }
    }

    public void add(Object[] objects){
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < size; j++) {
                if (elements[j] == null){
                    elements[j] = objects[i];
                    break;
                }
                if (j == size-1) throw new IllegalArgumentException("Bitset is full");
            }
        }
    }

    public void remove(int i) {
        elements[i] = null;
    }

    public void remove(T o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                elements[i] = null;
            }
        }
    }

    public void remove(Object[] objects){
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < size; j++) {
                if (elements[j] != null && objects[i] != null) {
                    if (elements[j].equals(objects[i])) {
                        elements[j] = null;
                        break;
                    }
                }
            }
        }
    }

    public boolean contains(T o) {
        if (o == null) return false;
        for (Object obj : elements) {
            if (obj != null) {
                if (obj.equals(o)) return true;

            }
        }
        return false;
    }

    Bitset<T> union(Bitset b2) {
        Bitset b3 = new Bitset(b2.size+size);
        for (Object o : elements) {
            if (!b3.contains(o)) b3.add(o);
        }
        for (Object o : b2.getElements()){
            if (!b3.contains(o)) b3.add(o);
        }
        return b3;
    }

    Bitset<T> intersect(Bitset b2) {
        Bitset<T> intersected = new Bitset<T>(size + b2.size);
        for (Object o1 : elements) {
            for (Object o2 : b2.elements) {
                if (o1 != null && o2 != null) {
                    if (!o1.getClass().equals(o2.getClass())) throw new IllegalArgumentException("Different raw types");
                    if (o1.equals(o2)) {
                        intersected.add((T)o1);
                    }
                }
            }
        }
        return intersected;
    }

    public Bitset<T> addition(Bitset b2){
        Bitset<T> result = new Bitset(size);
        result.add(elements);
        for (Object o1 : elements){
            for (Object o2 : b2.getElements()){
                if(o1.equals(o2)) {
                    result.remove((T)o1);
                }
            }
        }
        return result;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : elements) {
            if (o != null) {
                String appended;
                    appended = o.toString();
                sb.append(appended).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bitset bitset = (Bitset) o;
        return size == bitset.size &&
                Arrays.equals(elements, bitset.elements);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

}


