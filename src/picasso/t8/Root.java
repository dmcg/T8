package picasso.t8;

public class Root implements Comparable<Root> {
    public final String root;
    public final int frequency;

    public Root(String key, int frequency) {
        this.root = key;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Root{" +
                "root='" + root + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Root root1 = (Root) o;

        if (frequency != root1.frequency) return false;
        if (!root.equals(root1.root)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = root.hashCode();
        result = 31 * result + frequency;
        return result;
    }

    @Override
    public int compareTo(Root o) {
        int diff = o.frequency - frequency;
        return diff == 0 ? this.root.compareTo(o.root) : diff;
    }
}
