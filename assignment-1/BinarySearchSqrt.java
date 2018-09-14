public class BinarySearchSqrt {
    public static double sqrt (double x, double epsilon) {
        double upper = x;
        double lower = 0;
        double result = 0;
        double minimumAcceptedAnswer = x - (x * epsilon);
        double maximumAcceptedAnswer = x + (x * epsilon);
        System.out.printf("The maximum accepted answer is: %f\n", maximumAcceptedAnswer);
        System.out.printf("The minimum accepted answer is: %f\n", minimumAcceptedAnswer);
        while(result * result < minimumAcceptedAnswer || result * result > maximumAcceptedAnswer) {
            System.out.printf("The midpoint is: %f\n", result);
            System.out.printf("The upper is: %f\n", upper);
            System.out.printf("The lower is %f\n", lower);
            //Get the midpoint
            result = (upper + lower)/2;
            if(result * result > maximumAcceptedAnswer) {
                upper = result;
            } else if (result * result < minimumAcceptedAnswer) {
                lower = result;
            }
        }
        return result;
    }
}