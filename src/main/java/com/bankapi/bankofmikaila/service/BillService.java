package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Bill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillService {

    private static final Map<Long, Bill> billData = new HashMap<>();
    private static long nextBillId = 1;

    // Get all bills
    public List<Bill> getAllBills() {
        return new ArrayList<>(billData.values());
    }

    // Get a specific bill by ID
    public Bill getBillById(Long id) {
        return billData.get(id);
    }

    // Create a new bill
    public Bill createBill(Bill bill) {
        long newBillId = nextBillId++;
        bill.setId(newBillId);
        billData.put(newBillId, bill);
        return bill;
    }

    // Update an existing bill
    public Bill updateBill(Long id, Bill updatedBill) {
        if (billData.containsKey(id)) {
            updatedBill.setId(id);
            billData.put(id, updatedBill);
            return updatedBill;
        } else {
            return null;
        }
    }

    // Delete a bill
    public boolean deleteBill(Long id) {
        if (billData.containsKey(id)) {
            billData.remove(id);
            return true;
        } else {
            return false;
        }
    }
}

