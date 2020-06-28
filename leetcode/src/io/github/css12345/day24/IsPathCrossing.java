package io.github.css12345.day24;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode 5448.判断路径是否相交<br>
 * https://leetcode-cn.com/problems/path-crossing/
 * @author cs
 *
 */
public class IsPathCrossing {

	public static void main(String[] args) {
		String[] testDatas = { "NES", "NESWW", "SS" };
		boolean[] testResults = { false, true, false };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(testResults[i], new IsPathCrossing().isPathCrossing(testDatas[i]));
	}

	private static class Point {
		final int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point move(char dir) {
			int addX = 0, addY = 0;
			if (dir == 'N') {
				addY = 1;
			} else if (dir == 'S') {
				addY = -1;
			} else if (dir == 'W') {
				addX = -1;
			} else {
				addX = 1;
			}
			return new Point(x + addX, y + addY);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("(");
			builder.append(x);
			builder.append(",");
			builder.append(y);
			builder.append(")");
			return builder.toString();
		}

	}

	public boolean isPathCrossing(String path) {
		Set<Point> visitedPoints = new HashSet<>();
		Point currentPoint = new Point(0, 0);
		visitedPoints.add(currentPoint);
		for (int i = 0; i < path.length(); i++) {
			Point movedPoint = currentPoint.move(path.charAt(i));
			if (visitedPoints.contains(movedPoint))
				return true;
			visitedPoints.add(movedPoint);
			currentPoint = movedPoint;
		}
		return false;
	}
}
