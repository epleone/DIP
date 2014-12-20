package cn.basic;

/**
 * 2014 July 28
 * @author eple
 * Guass 模糊
 */
public class Guass {
	public double[][] mask;
	public Guass(int n,float sigma){
		this.mask = MakeGauss(n, sigma);
	}
	
	private double[][]  MakeGauss(int nSize,float sigma)
	{
		 double[][] dResult = new double[nSize][nSize];    // 用于存储结果
		 int nCenterX = (nSize-1)/2;
		 int nCenterY = nCenterX;  
		 double  temp;
		 double  Sum = 0;
		 
		 for(int i = 0; i< nSize; ++i){
			 	for(int j = 0; j < nSize; ++j){
			 	   temp = (i - nCenterX) * (i - nCenterX) + (j  - nCenterY) * (j - nCenterY);
				   dResult[i][j] = (float)( Math.exp( - temp / (2 * sigma * sigma) ) / Math.sqrt(2 * Math.PI * sigma * sigma) );
				   Sum += dResult[i][j];
			 	}
		 }
		 
		 // 归一化
		 for(int i = 0; i< nSize; ++i){
			 for(int j = 0; j < nSize; ++j){
				 dResult[i][j] = dResult[i][j] / Sum;
			 }
		 }
//		 
		 return dResult;
	}
}
