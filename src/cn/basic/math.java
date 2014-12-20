package cn.basic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author eple
 *
 */
public class math {
	/*
	 * find the max value
	 */
	public static int findMaxValue(int[] p) {  
        int max = -1;  
        for(int i=0; i<p.length; i++) {  
            if(max < p[i]) {  
                max = p[i];  
            }  
        }  
        return max;  
    }
	
	/*
	 * Subscript max,just return the first find value
	 */
	public static int maxIndex(double[] p) {
		double[] temp = new double[p.length];
		System.arraycopy(p,0,temp,0,temp.length);
		Arrays.sort(temp);
		
		double a=0;
		for(int i=p.length-1;i>p.length-2;i--){
			a = temp[i];
		}
		
		int res = 0;
		for(int i=0;i<p.length;i++){
			if(a == p[i]){
				res = i;
			}
		}
		return res;
	}
	
	/*
	 * Subscript max,just return the first find value
	 */
	public static int[] maxIndex(int[][] p) {
		int max = 0;
		
		for(int[] s:p){
			if(max < findMaxValue(s))
				max = findMaxValue(s);
		}
		
		int[] res = new int[2];
		
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[1].length;j++){
				if(max == p[i][j]){
					res[0] = i;
					res[1] = j;
					return res;
				}
			}
		}
		return res;
	}
	
	/*
	 * Subscript max,just return the first find value
	 */
	public static ArrayList<h> maxIndex(int[][] p,int rate) {
		ArrayList<h> res = new ArrayList<h>();
		int max = 0;
		for(int[] s:p){
			if(max < findMaxValue(s))
				max = findMaxValue(s);
		}
		
		int m = max*rate/100;
		
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[1].length;j++){
				if(m < p[i][j]){
					res.add(new h(i, j));
				}
			}
		}
		return res;
	}
	
	/*
	 * return sum
	 */
	public static int sum(int[] p){
		int sum=0;
		
		for(int i=0;i<p.length;i++)
			sum += p[i];
		
		return sum;
	}
	
	public static double sum(double[] p){
		double sum=0;
		
		for(int i=0;i<p.length;i++)
			sum += p[i];
		
		return sum;
	}
	/*
	 * return Pre-k items' sum
	 */
	public static double sum(double[] p,int k){
		double sum=0;
		
		if(k > p.length)
			k = p.length;
		
		for(int i=0;i< k;i++)
			sum += p[i];
		
		return sum;
	}
	
	public static double sum(double[][] p){
		double sum=0;
		
		for(double[] s:p)
			sum += sum(s);
		
		return sum;
	}
	/*
	 * Normalized
	 */
	public static double[] Norm(int[] p){
		double[] res = new double[p.length];
		double sum = 1.0*sum(p);
		
		for(int i=0;i<p.length;i++)
			res[i] = p[i]/sum;
		
		return res;
	}
	
	public static int st(int c)  
    {  
        return c > 255 ? 255 :( (c < 0) ? 0: c);  
    }
	
	public static int st(double c)  
    {  
        return st((int)(c));  
    }
	
	/*
	 * Image Convolution
	 */
	public static int imgConv(double[][] mask,int pix){
		int rgb = 0;
		int len = mask[1].length;
		int[] p = new int[5];
		p[0] = (pix >> 16) & 0xFF; //R
		p[1] = (pix >> 8) & 0xFF;  //G
		p[2] = (pix >> 0) & 0xFF;  //B
		p[3] = (pix >> 24) & 0xff; //a
		p[4] = 1;
		double[] Res = new double[4];
		
        if(len != 5){
        	System.out.println("The ColorMatrix isn't 5 column!");
        	return rgb;
        }
        
        for(int i=0;i<4;i++){
        	for(int j=0;j<5;j++){
        		Res[i] += mask[i][j]*p[j];
        	}
        }
        
        rgb = (st(Res[3]) << 24) | (st(Res[0]) << 16) | (st(Res[1]) << 8) | st(Res[2]);
		return rgb;
	}
}

