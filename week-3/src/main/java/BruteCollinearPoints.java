import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// this works only with 4-line segments
public class BruteCollinearPoints implements CollinearPoints {

    private final List<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] pointsArg) {
        if (pointsArg == null)
            throw new IllegalArgumentException("points cannot be null");

        Point[] points = Arrays.copyOf(pointsArg, pointsArg.length);

        // validations
        for (Point p : points)
            if (p == null)
                throw new IllegalArgumentException("point cannot be null");

        Arrays.sort(points);

        Point lastPoint = null;
        for (Point p : points) {
            if (lastPoint != null && p.compareTo(lastPoint) == 0)
                throw new IllegalArgumentException("Points cannot be equal");

            lastPoint = p;
        }

        // as the array is sorted, skipping reiterating over previous nodes is fine
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                double slopeQ = p.slopeTo(q);

                for (int t = j + 1; t < points.length; t++) {
                    Point r = points[t];
                    double slopeR = p.slopeTo(r);
                        // if it's already different, no reason to continue inner iterations
                    if (slopeQ != slopeR)
                        continue;

                    for (int z = t + 1; z < points.length; z++) {
                        Point s = points[z];
                        double slopeS = p.slopeTo(s);
                        // if slopes are equal, it is a line segment
                        if (slopeQ == slopeR && slopeR == slopeS)
                            segments.add(new LineSegment(p, s));
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] toReturn = new LineSegment[segments.size()];
        segments.toArray(toReturn);
        return toReturn;
    }
}
