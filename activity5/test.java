
public class test {
    public static void main(String args[]){
        int n = 6; 
        int t = 3; 

        double points[][] = {
            {3.0,2578.0},
            {4.0,3402.0},
            {5.0,4414.0}
        };

        double message = 0;
        for(int i = 0; i < t; i++){
            double l = 1;
            for(int j = 0; j < t; j++){
                if(j == i) continue;
                l = ((0.0 - points[j][0]) / (points[i][0] - points[j][0])) * l;
            }
            message += (points[i][1] * l);
        }
        System.out.println((int) message);
    }
}
