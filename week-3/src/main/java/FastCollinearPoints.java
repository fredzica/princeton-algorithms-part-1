import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints implements CollinearPoints {

    private final List<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] pointsArg) {
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

        for (Point origin : points) {
            List<Point> otherPoints = new ArrayList<>(Arrays.asList(points));
            otherPoints.remove(origin);

            // sorting by slope order relative to origin
            otherPoints.sort(origin.slopeOrder());
            int count = 0;
            LineSegment segment = null;
            for (int j = otherPoints.size() - 1; j > 0; j--) {
                Point previous = otherPoints.get(j - 1);

                if (origin.slopeTo(previous) == origin.slopeTo(otherPoints.get(j))) {
                    count++;

                    // if the slope is the same, it's potentially the start of a line segment
                    if (count == 1)
                        segment = new LineSegment(origin, otherPoints.get(j));
                } else {
                    // when the slope changes, adds the previous segment if it's valid (two pairs at least)
                    if (segment != null && count >= 2) {
                        segments.add(segment);
                        segment = null;
                    }
                    count = 0;
                }

                // if the current origin is not one of the endpoints, discard the created segment
                if (origin.compareTo(previous) >= 0) {
                    count = 0;
                    segment = null;
                }
            }

            // add segments that contained the last point of the loop
            if (segment != null && count >= 2)
                segments.add(segment);
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
