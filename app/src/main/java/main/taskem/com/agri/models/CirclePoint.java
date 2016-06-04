package main.taskem.com.agri.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by atul.bhardwaj on 04/06/16.
 */

public class CirclePoint implements Parcelable {
	public int x;
	public int y;
	public int r;

	public CirclePoint() {}

	public CirclePoint(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public CirclePoint(CirclePoint src) {
		this.x = src.x;
		this.y = src.y;
		this.r = src.r;
	}

	/**
	 * Set the point's x and y coordinates
	 */
	public void set(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * Negate the point's coordinates
	 */
	public final void negate() {
		x = -x;
		y = -y;
		r = -r;
	}

	/**
	 * Offset the point's coordinates by dx, dy
	 */
	public final void offset(int dx, int dy) {
		x += dx;
		y += dy;
	}

	/**
	 * Returns true if the point's coordinates equal (x,y)
	 */
	public final boolean equals(int x, int y, int r) {
		return this.x == x && this.y == y && this.r == r;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CirclePoint point = (CirclePoint) o;

		if (x != point.x) return false;
		if (y != point.y) return false;
		if (r != point.r) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "CirclePoint(" + x + ", " + y +  ", " +r +")";
	}

	/**
	 * Parcelable interface methods
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Write this point to the specified parcel. To restore a point from
	 * a parcel, use readFromParcel()
	 * @param out The parcel to write the point's coordinates into
	 */
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(r);
	}

	public static final Parcelable.Creator<CirclePoint> CREATOR = new Parcelable.Creator<CirclePoint>() {
		/**
		 * Return a new point from the data in the specified parcel.
		 */
		public CirclePoint createFromParcel(Parcel in) {
			CirclePoint circlePoint = new CirclePoint();
			circlePoint.readFromParcel(in);
			return circlePoint;
		}

		/**
		 * Return an array of rectangles of the specified size.
		 */
		public CirclePoint[] newArray(int size) {
			return new CirclePoint[size];
		}
	};

	/**
	 * Set the point's coordinates from the data stored in the specified
	 * parcel. To write a point to a parcel, call writeToParcel().
	 *
	 * @param in The parcel to read the point's coordinates from
	 */
	public void readFromParcel(Parcel in) {
		x = in.readInt();
		y = in.readInt();
		r = in.readInt();
	}
}

