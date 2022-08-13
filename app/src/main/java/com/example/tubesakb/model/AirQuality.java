package com.example.tubesakb.model;

import com.google.gson.annotations.SerializedName;

public class AirQuality{

	@SerializedName("no2")
	private double no2;

	@SerializedName("o3")
	private double o3;

	@SerializedName("us-epa-index")
	private int usEpaIndex;

	@SerializedName("so2")
	private double so2;

	@SerializedName("pm2_5")
	private double pm25;

	@SerializedName("pm10")
	private double pm10;

	@SerializedName("co")
	private double co;

	@SerializedName("gb-defra-index")
	private int gbDefraIndex;

	public double getNo2(){
		return no2;
	}

	public double getO3(){
		return o3;
	}

	public int getUsEpaIndex(){
		return usEpaIndex;
	}

	public double getSo2(){
		return so2;
	}

	public double getPm25(){
		return pm25;
	}

	public double getPm10(){
		return pm10;
	}

	public double getCo(){
		return co;
	}

	public int getGbDefraIndex(){
		return gbDefraIndex;
	}
}