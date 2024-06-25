package com.nik.ecom.services.admin.adminOrder;

import java.util.List;

import com.nik.ecom.dto.AnalyticsResponse;
import com.nik.ecom.dto.OrderDto;

public interface AdminOrderService {
	List<OrderDto> getAllPlacedOrders();
	
	OrderDto changeOrderStatus(Long orderId, String status);
	
	AnalyticsResponse calculateAnalytics();
}
