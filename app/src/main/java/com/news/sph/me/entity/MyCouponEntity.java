package com.news.sph.me.entity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class MyCouponEntity {
    public String getCouponRedeemCode() {
        return couponRedeemCode;
    }

    public void setCouponRedeemCode(String couponRedeemCode) {
        this.couponRedeemCode = couponRedeemCode;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getCouponMoneyEqual() {
        return CouponMoneyEqual;
    }

    public void setCouponMoneyEqual(String couponMoneyEqual) {
        CouponMoneyEqual = couponMoneyEqual;
    }

    public String getCouponRangeOfUse() {
        return couponRangeOfUse;
    }

    public void setCouponRangeOfUse(String couponRangeOfUse) {
        this.couponRangeOfUse = couponRangeOfUse;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getDiscountNumber() {
        return DiscountNumber;
    }

    public void setDiscountNumber(String discountNumber) {
        DiscountNumber = discountNumber;
    }

    public String getCouponExpirationTime() {
        return couponExpirationTime;
    }

    public void setCouponExpirationTime(String couponExpirationTime) {
        this.couponExpirationTime = couponExpirationTime;
    }

    public String getIstouse() {
        return istouse;
    }

    public void setIstouse(String istouse) {
        this.istouse = istouse;
    }

    public String getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(String isoverdue) {
        this.isoverdue = isoverdue;
    }

    public String getCouponRegister() {
        return CouponRegister;
    }

    public void setCouponRegister(String couponRegister) {
        CouponRegister = couponRegister;
    }

    public String getCouponRedeemName() {
        return CouponRedeemName;
    }

    public void setCouponRedeemName(String couponRedeemName) {
        CouponRedeemName = couponRedeemName;
    }

    public String getH5Url() {
        return H5Url;
    }

    public void setH5Url(String h5Url) {
        H5Url = h5Url;
    }

    /**
     * 优惠券兑换码
     */
    private String couponRedeemCode;
    /**
     * 满减【满金额】
     */
    private String couponmoney;
    /**
     * 满减【减金额】、直减金额、免费金额
     */
    private String CouponMoneyEqual;
    /**
     * 属性 服务券，优惠券
     */
    private String couponRangeOfUse;
    /**
     * 优惠券种类 打折、满减、免费、直减
     */
    private String couponType;
    /**
     * 打折数(7折、7.5折) 种类是打折时使用
     */
    private String DiscountNumber;
    /**
     * 过期时间
     */
    private String couponExpirationTime;
    /**
     * 是否使用  0未使用/1已使用
     */
    private String istouse;
    /**
     * 是否过期‘’DataOn’’未过期/’DataOff’提示已过期
     */
    private String isoverdue;

    /**
     * 是否注册券
     */
    private String CouponRegister;
    /**
     * 优惠券名称
     */
    private String CouponRedeemName;
    /**
     * H5显示路径(只有服务券有url)
     */
    private String H5Url;
}
