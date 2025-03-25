package com.sprint1.plantnursery.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.plantnursery.entity.OrderTable;
import com.sprint1.plantnursery.entity.Plant;
import com.sprint1.plantnursery.exceptions.OrderIdNotFoundException;
import com.sprint1.plantnursery.exceptions.PlantIdNotFoundException;
import com.sprint1.plantnursery.repository.IOrderRepository;

/*Controller Class for Order Controller
Created By: Arigela Raghuram
*/

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;

	@Override
	public OrderTable addOrder(OrderTable order) {
		//orderRepository.save(order);
		//return order;   //Hussain-13
		OrderTable printOrder=orderRepository.save(order);
		return printOrder;
	}

	/* Previous Code: 
	 * public OrderTable updateOrder(OrderTable order,int bookingId) {
	 * 		Optional<OrderTable> orderToBeUpdated = orderRepository.findById(order.getBookingOrderId());
	 * 			if(orderToBeUpdated.isPresent()){
	 * 				orderRepository.save(order); //Hussain-13
	 * 			}
	 * return orderToBeUpdated.orElseThrow(
				() -> new OrderIdNotFoundException("Order with id: " + order.getBookingOrderId() + " is not found"));
	   }*/
	//New Code: (changed)
	@Override
	public OrderTable updateOrder(OrderTable order,int bookingId) {
		Optional<OrderTable> orderToBeUpdated = orderRepository.findById(bookingId);
		if (orderToBeUpdated.isPresent()) {
			OrderTable oTable=orderToBeUpdated.get();
			/*oTable.setOrderDate(order.getOrderDate());
			oTable.setPrice(order.getPrice());
			oTable.setQuantity(order.getQuantity());
			oTable.setTransactionMode(order.getTransactionMode());*/     //Hussain-13
			if(order.getOrderDate()!=null) {
				oTable.setOrderDate(order.getOrderDate());
			}
			if(order.getPrice()!=0) {
				oTable.setPrice(order.getPrice());
			}
			if(order.getQuantity()!=0) {
				oTable.setQuantity(order.getQuantity());
			}
			if(order.getTransactionMode()!=null) {
				oTable.setTransactionMode(order.getTransactionMode());
			}
			orderRepository.save(oTable);
		}
		return orderToBeUpdated.orElseThrow(
				() -> new OrderIdNotFoundException("Order with id: " + bookingId+ " is not found"));
	}

	@Override
public OrderTable deleteOrder(int bookingId){
		
		Optional<OrderTable> orderOptional = orderRepository.findById(bookingId);
		
		if(orderOptional.isPresent()) {
			OrderTable hear = orderOptional.get();
			orderRepository.delete(hear);
			return hear;
		}
		return orderOptional.orElseThrow(() -> new OrderIdNotFoundException("Order Not Found with Id : "+bookingId));
	}

	@Override
	public OrderTable viewOrder(int bookingId) throws OrderIdNotFoundException{
		Optional<OrderTable> orderOptional = orderRepository.findById(bookingId);
		return orderOptional
				.orElseThrow(() -> new OrderIdNotFoundException("Order with id: " + bookingId + " is not found"));
	}

	@Override
	public List<OrderTable> viewAllOrders() {
		//return orderRepository.findAll();    //Hussain-13
		List<OrderTable> list=orderRepository.findAll();
		if(list.isEmpty()) {
			throw new OrderIdNotFoundException("No order records found");
		}
		return list;
	}
}