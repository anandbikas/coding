package com.anand.coding.problems;


/**
 *
 */
public class _05_OverlappingRectangleArea {
    
    private static class Point{
        long x,y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private static class Rectangle{
        Point ll;
        Point ur;

        public Rectangle(Point ll, Point ur) {
            this.ll = ll;
            this.ur = ur;
        }

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.ll = new Point(x1,y1);
            this.ur = new Point(x2,y2);
        }

        public long area(){

            //Point validation
            if(ll.x>ur.x || ll.y>ur.y){
                return 0;
            }
            return (ur.x-ll.x) * (ur.y-ll.y);
        }

        @Override
        public String toString() {
            return "[" + ll + ur + "]";
        }
    }


    /**
     *
     * @param R1
     * @param R2
     * @return
     */
    public static Rectangle overlappingRectangle(Rectangle R1, Rectangle R2){

        long x1=Math.max(R1.ll.x, R2.ll.x);    long x2=Math.min(R1.ur.x, R2.ur.x);

        long y1=Math.max(R1.ll.y, R2.ll.y);    long y2=Math.min(R1.ur.y, R2.ur.y);

        return new Rectangle(new Point(x1,y1), new Point(x2,y2));
    }


    public static void main(String [] args){

        Rectangle R3 = overlappingRectangle(new Rectangle(1,1, 3,3), new Rectangle(0,2,2,4));
        System.out.println(R3 + "Area: " + R3.area());

        R3 = overlappingRectangle(new Rectangle(1,1, 3,3), new Rectangle(1,0,4,2));
        System.out.println(R3 + "Area: " + R3.area());

        R3 = overlappingRectangle(new Rectangle(1,1, 3,3), new Rectangle(4,0,6,2));
        System.out.println(R3 + "Area: " + R3.area());

        R3 = overlappingRectangle(new Rectangle(0,0, 1,1), new Rectangle(2,2, 3,3));
        System.out.println(R3 + "Area: " + R3.area());

        R3 = overlappingRectangle(new Rectangle(-193634870,-175701756,958697367,607619635),
                new Rectangle(91619846,10349052,822028072,696611776));
        System.out.println(R3 + "Area: " + R3.area());
        //Note if area is >0, then rectangles are overlapping.
    }
}
