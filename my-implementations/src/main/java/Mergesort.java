import java.util.Arrays;

public class Mergesort {

    public int[] sort(int[] arr) {
        int[] aux = Arrays.copyOf(arr, arr.length);
        if (arr.length == 0)
            return aux;

        this.sort(arr, aux, 0, arr.length - 1);
        return arr;
    }

    private void sort(int[] arr, int[] aux, int lo, int hi) {
        if (lo == hi)
            return;

        int diff = hi - lo;
        int midpoint = lo + diff/2;

        this.sort(arr, aux, lo, midpoint);
        this.sort(arr, aux, midpoint + 1, hi);
        this.merge(arr, aux, lo, midpoint, hi);
    }

    private void merge(int[] arr, int[] aux, int lo, int midpoint, int hi) {
        int i1 = lo;
        int i2 = midpoint + 1;

        for (int i = lo; i <= hi; i++)
            aux[i] = arr[i];

        for (int i = lo; i <= hi; i++)
            if (i2 > hi)                arr[i] = aux[i1++];
            else if (i1 > midpoint)     arr[i] = aux[i2++];
            else if (aux[i1] > aux[i2]) arr[i] = aux[i2++];
            else                        arr[i] = aux[i1++];
    }
}
