package com.nik.ecom.services.admin.coupon;

import java.util.List;

import com.nik.ecom.entity.Coupon;

public interface AdminCouponService {

	Coupon createCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();
	
}
