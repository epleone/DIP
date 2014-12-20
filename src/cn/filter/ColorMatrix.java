package cn.filter;
/**
 * link:http://tieba.baidu.com/p/1730549463#
 * link:http://deeryrl.blog.163.com/blog/static/1525428742012931112616825/
 * link:http://blog.csdn.net/easyer2012/article/details/8053161
 * @author eple
 *
 */
public class ColorMatrix {
	public static double[][] Gray = { {0.33,0.59,0.10,0,0},//R
									  {0.33,0.59,0.10,0,0},//G
									  {0.33,0.59,0.10,0,0},//B
									  {0,0,0,1,0},//a
	};
	
	public static double[][] lomo = { {1.7f,  0.1f, 0.1f, 0, -73.1f},

			{0,  1.7f, 0.1f, 0, -73.1f},

			{0,  0.1f, 1.6f, 0, -73.1f},

			{0,  0, 0, 1.0f, 0}
		  };
	
	public static double[][] invert = { {-1f,  0f, 0f, 1, 0f},

		{0,  -1f, 0f, 1, 0f},

		{0,  0f, -1f, 1, 0f},

		{0,  0, 0, 1.0f, 0}
	  };
	
	public static double[][] toBGR = { {0f,  0f, 1f, 0, 0f},

		{0,  1f, 0f, 0, 0f},

		{1,  0f, 0f, 0, 0f},

		{0,  0, 0, 1.0f, 0}
	  };
	
		/*
		 * 灰褐色
		 */
	  public static double[][] Sepia = { {0.393f,  0.769f, 0.189f, 0, 0f},

			{0.349,  0.686f, 0.168f, 0, 0f},

			{0.272,  0.534f, 0.131f, 0, 0f},

			{0,  0, 0, 1.0f, 0}
		  };
	  
	  public static double[][] binrylike = { {1.5f,  1.5f, 1.5f, -1, 0f},

		  	{1.5f,  1.5f, 1.5f, -1, 0f},

		  	{1.5f,  1.5f, 1.5f, -1, 0f},

			{0,  0, 0, 1.0f, 0}
		  };
	  
	  public static double[][] Polaroid = { {1.438f,  -0.122f, -0.016f, -0.03, 0f},

		  	{-0.062f,  1.378f, -0.016f, 0.05, 0f},

		  	{-0.062f,  -0.122f, 1.483f, -0.02, 0f},

			{0,  0, 0, 1.0f, 0}
		  };
	  
	  /*
	   * Magenta 洋红
	   */
	  public static double[][] Magenta = { {0.85f,  0f, 0.35f, 0, 50f},

			{0,  1f, 0f, 0, 50f},

			{0,  0f, 1f, 0, 50f},

			{0,  0, 0, 1.0f, 0}
		  };
	  
		public static double[][] addBright = { {1f,  0f, 0f, 0, 50f},

			{0,  1f, 0f, 0, 50f},

			{0,  0f, 1f, 0, 50f},

			{0,  0, 0, 1.0f, 0}
		  };
		
		
		/*
		 * site:http://www.eoeandroid.com/thread-176490-1-1.html
		 * 效果不对，why？
		 */
		public static double[][] ice = { {1.5f,  -1.5f, -1.5f, 0, 0f},

			{-1.5,  1.5f, -1.5f, 0, 0f},

			{-1.5,  -1.5f, 1.5f, 0, 0f},

			{0,  0, 0, 1.0f, 0}
		  };
}
