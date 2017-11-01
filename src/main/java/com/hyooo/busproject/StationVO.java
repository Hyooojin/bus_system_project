package com.hyooo.busproject;

public class StationVO {
	private String centerYn;
	private String districtCd;
	private String mobileNo;
	private String regionName;
	private String stationId;
	private String stationName;
	private String x;
	private String y;
	private String stationSeq;
	private String turnYn;
	public String getCenterYn() {
		return centerYn;
	}
	public void setCenterYn(String centerYn) {
		this.centerYn = centerYn;
	}
	public String getDistrictCd() {
		return districtCd;
	}
	public void setDistrictCd(String districtCd) {
		this.districtCd = districtCd;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getStationSeq() {
		return stationSeq;
	}
	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}
	public String getTurnYn() {
		return turnYn;
	}
	public void setTurnYn(String turnYn) {
		this.turnYn = turnYn;
	}
	@Override
	public String toString() {
		return "StationVO [centerYn=" + centerYn + ", districtCd=" + districtCd + ", mobileNo=" + mobileNo
				+ ", regionName=" + regionName + ", stationId=" + stationId + ", stationName=" + stationName + ", x="
				+ x + ", y=" + y + ", stationSeq=" + stationSeq + ", turnYn=" + turnYn + "]";
	}

	
}
