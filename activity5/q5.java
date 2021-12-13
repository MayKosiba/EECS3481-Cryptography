import java.util.Random;

public class q5 {
    public static void main(String args[]){
        //constraints
        int S = 44;
        int N = 5;
        int K = 3; 

        //creating polynomial constants
        Random rand = new Random();
        int A = rand.nextInt(100);
        int C = S;
        double[][] points = new double[N][2];

        //assigning shares
        for(int i = 1; i <= N; i++){
            points[i-1][0] = i;
            double y = 0;
            for(int j = 1; j < K; j++)
                y += A * Math.pow(i, j);
            points[i-1][1] = y + C;
        }

        //Reconstructing
        int nKeys = rand.nextInt((N+1)-K) + K;
        double message = 0;
        for(int i = 0; i < nKeys; i++){
            double l = 1;
            for(int j = 0; j < nKeys; j++){
                if(j == i) continue;
                l = ((0 - points[j][0]) / (points[i][0] - points[j][0])) * l;
            }
            message += (points[i][1] * l);
        }
        System.out.println((int) message);
    }
}