package com.doesitfit;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);

	    Integer[] parcelSize = new Integer[]{in.nextInt(), in.nextInt()};
        double maxCircleRadius = (double)Math.min(parcelSize[0], parcelSize[1]) / 2;
	    int shapesCount = in.nextInt();

        for (int i = 0; i < shapesCount; i++) {

            if(in.next().trim().equals("C")) {
                int radius = in.nextInt();
                if (radius<=maxCircleRadius) print("YES");
                else print("NO");
            } else {
                int l1 = in.nextInt();
                int l2 = in.nextInt();
                if (l1<=parcelSize[0] && l2<=parcelSize[1] || l1<=parcelSize[1]&&l2<=parcelSize[0]) print("YES"); // if both sides of inner rectangle are smaller than parcel
                else if (l1>parcelSize[0] && l2>parcelSize[1] || l1>parcelSize[1]&&l2>parcelSize[0]) print("NO"); // if both sides of inner rectangle are bigger than parcel
                else if (fitsOblique(parcelSize[0], parcelSize[1], l1, l2)) print("YES"); // if inner rectangle fits outer rectangle
                else print("NO");
            }
        }
    }

    /**
     * Fit a rotated rectangle inside another rectangle
     * https://www.jstor.org/stable/2691523?seq=1#page_scan_tab_contents
     *
     * Condition: p1>p2 ** l1>l2
     * Formula: p > a && b >= (2pqa+(p^2-q^2)Math.sqrt(p^2+q^2-a^2))/(p^2+q^2)
     */
    private static boolean fitsOblique(double p1, double p2, double l1, double l2) {
        double temp;
        if (p1<p2) { temp = p1; p1 = p2; p2 = temp; }
        if (l1<l2) { temp = l1; l1 = l2; l2 = temp; }

        return (l1 > p1 &&
        p2 >= ((2*l1*l2*p1+(Math.pow(l1,2)-Math.pow(l2,2))*Math.sqrt(Math.pow(l1,2)+Math.pow(l2,2)-Math.pow(p1,2))) / (Math.pow(l1,2)+Math.pow(l2,2))));
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
