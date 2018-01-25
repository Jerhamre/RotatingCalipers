import java.awt.geom.Point2D;
import java.util.List;


public class Caliper {

        private double SIGMA = 0.00000000001;

        private List<Node> convexHull;
        private int pointIndex;
        private double currentAngle;

        public Caliper(List<Node> convexHull2, int pointIndex, double currentAngle) {
            this.convexHull = convexHull2;
            this.pointIndex = pointIndex;
            this.currentAngle = currentAngle;
        }

        public double getAngleNextPoint() {

            Node p1 = convexHull.get(pointIndex);
            Node p2 = convexHull.get((pointIndex + 1) % convexHull.size());

            double deltaX = p2.getX() - p1.getX();
            double deltaY = p2.getY() - p1.getY();

            double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

            return angle < 0 ? 360 + angle : angle;
        }

        public double getConstant() {

            Node p = convexHull.get(pointIndex);

            return p.getY() - (getSlope() * p.getX());
        }

        public double getDeltaAngleNextPoint() {

            double angle = getAngleNextPoint();

            angle = angle < 0 ? 360 + angle - currentAngle : angle - currentAngle;

            return angle < 0 ? 360 : angle;
        }

        Point2D.Double getIntersection(Caliper that) {

           
            double x;
            double y;

            if(this.isVertical()) {
                x = convexHull.get(pointIndex).getX();
            }
            else if(this.isHorizontal()) {
                x = that.convexHull.get(that.pointIndex).getX();
            }
            else {
                x = (that.getConstant() -  this.getConstant()) / (this.getSlope() - that.getSlope());
            }

            if(this.isVertical()) {
                y = that.getConstant();
            }
            else if(this.isHorizontal()) {
                y = this.getConstant();
            }
            else {
                y = (this.getSlope() * x) + this.getConstant();
            }

            return new Point2D.Double(x, y);
        }

        public double getSlope() {
            return Math.tan(Math.toRadians(currentAngle));
        }

        public boolean isHorizontal() {
            return (Math.abs(currentAngle) < SIGMA) || (Math.abs(currentAngle - 180.0) < SIGMA);
        }

        public boolean isVertical() {
            return (Math.abs(currentAngle - 90.0) < SIGMA) || (Math.abs(currentAngle - 270.0) < SIGMA);
        }

        public void rotateBy(double angle) {

            if(this.getDeltaAngleNextPoint() == angle) {
                pointIndex++;
            }

            this.currentAngle += angle;
        }
        
        public double getcurrentAngle(){
        	return currentAngle;
        }
    }