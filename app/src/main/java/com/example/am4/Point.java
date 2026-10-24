package com.example.am4;

import android.os.Parcel;
import android.os.Parcelable;

public class Point implements Parcelable {
    private final int x;
    private final int y;
    private final int color;

    public Point(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    protected Point(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        color = in.readInt();
    }

    public String getDescription() {
        return "x=" + x + " y=" + y + " kolor=" + color;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };
}
